<template>
  <div class="container">
    <div class="listItem">
      <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>订单信息</el-breadcrumb-item>
      </el-breadcrumb>
      <div class="rightInput" style="margin-right: 50px">
        <el-input v-model.lazy="findNewsPageInfo.keyWords" placeholder="搜索订单"></el-input>
        <!-- <el-button   type="primary">搜索</el-button> 根据订单内容（花名）搜索-->
      </div>
      
      <!-- 列表 -->
      
           <el-table :data="pageData" style="width: 100%">
      <el-table-column label="序号" prop="o_id"></el-table-column>
      <el-table-column label="鲜花名称" prop="f_name"></el-table-column>
      <el-table-column label="预留电话" prop="o_phone_num"></el-table-column>
      <el-table-column label="客户电话" prop="phone_num"></el-table-column>
      <el-table-column label="地址" prop="o_area"></el-table-column>
      <el-table-column label="备注" prop="o_note"></el-table-column>
      <el-table-column label="下单时间" prop="o_time"></el-table-column>
        <el-table-column  >
        
      
      <template  v-slot="scope">
                <el-button icon="plus" style="width: 50px" @click="observe(scope.row)">详请信息</el-button>
        
      </template>
        </el-table-column>
        <el-table-column  label = "操作" >
        
      
      <template v-slot="scope">
                <el-button icon="plus" style="width: 50px" @click="removewill(scope.row)">删除订单</el-button>
      </template>
        </el-table-column>
      </el-table>
        
         
        

      <!-- 分页器 -->
      <div style="margin-top: 20px">
        <el-pagination
          v-model:current-page="findNewsPageInfo.pageNum"
          v-model:page-size="findNewsPageInfo.pageSize"
          @size-change="getPageList"
          @current-change="getPageList"
          :page-sizes="[5, 7, 10]"
          background
          layout="prev, pager, next , ->, sizes, total"
          :total="totalSize"
        />
      </div>
              <div>
                <!--      订单详情信息弹窗  -->
                <el-dialog v-model="firstvisible" title="查看详情" width="30%" @close="cancel">

                    <el-form :model="temppageData" :rules="rules" label-width="120px">

                        <el-form-item label="预留电话" >
                             <el-input v-model="temppageData.o_phone_num"  style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="预留地址" >
                             <el-input v-model="temppageData.o_area"  style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="订单备注" >
                             <el-input v-model="temppageData.o_note"  style="width: 80%"></el-input>
                        </el-form-item>

                    </el-form>
                    <template #footer>
            <span class="dialog-footer">
              <el-button @click="cancel">取 消</el-button>
              
              <el-button type="primary" @click="save(temppageData)">确 定</el-button>
               
            </span>
                    </template>
                </el-dialog>
            </div>
            <div>
                <!--      退订信息弹窗  -->
                <el-dialog v-model="secondvisible"  title="退订" width="30%" @close="cancel1">
                    真的要退订吗
                <template #footer>
                   <el-form :model="temppageData" :rules="rules" label-width="120px"></el-form>
            <span class="dialog-footer">
              
              <el-button @click="cancel1">取 消</el-button>
              <el-button type="primary" @click="removeOrder(temppageData)">确 定</el-button>
               
            </span>
                    </template>
                </el-dialog>
            </div>
    </div>
  </div>
</template>
<script >
//   import { getfindNewsPageInfo , removeByHid } from "../../api/index"
import { defineComponent } from "vue";
export default defineComponent({
  name: "HeadlineNews",
});
</script>
<script  setup>
import request from "@/utils/index";
import { ref, onMounted, watch } from "vue";

import { ElMessage } from 'element-plus'



const identity=ref({});
const findNewsPageInfo = ref({
  keyWords: "", // 搜索关键字
  pageNum: 1, // 页码数
  pageSize: 5, // 页大小
});
const totalSize = ref(0); //分页总数量
// 初始化列表数据
const pageData = ref([
  {             o_id:"",
                f_id:"",
                f_name:"",
                uid:"",
                o_phone_num:"",
                phone_num:"",
                o_area:"",
                o_note:"",
                o_time:""
  },
]);
const temppageData = ref([

  {             
                o_id:"",
                f_id:"",
                f_name:"",
                uid:"",
                o_phone_num:"",
                phone_num:"",
                o_area:"",
                o_note:"",
                o_time:""
  },
]);
const firstvisible = ref(false)
const secondvisible = ref(false)
const removewill = (info) =>{
  secondvisible.value = true
  temppageData.value = info;
}
const removeOrder = (info) => {
  request.post("/removeOrder", info).then((res) => {
                        if (res.code === 200) {
                            ElMessage({
                                message: "删除订单成功",
                                type: "success",
                            });
                            getPageList()
                            
                        } else {
                        ElMessage({
                          message: res.msg,
                          ype: "error",
                      });
                  }
              });
}
//接收header组件用户搜索的数据
// Bus.on('keyword', (keywords) => {
//   findNewsPageInfo.value.keyWords = keywords
// })
// header点击切换高亮的时候传递过来的tid
// Bus.on('tid', (type) => {
//   findNewsPageInfo.value.type = type
// })
// 监视初始化参数findNewsPageInfo.value,的变化,当type发生改变的时候重新发送请求获取列表数据
//根据姓名搜索用户
watch(
  () => findNewsPageInfo.value,
  () => {
    if(identity.value === 'user'){
     getOwnPageList();
  }else if(identity.value === 'manager'){
    getPageList();
  }
  },
  {
    deep: true,
  }
);
//<el-button @click="cancel">取 消</el-button>
// <el-button type="primary" @click="save">确 定</el-button>
const cancel1 = () => {
  secondvisible.value = false
}
const cancel = () => {
  firstvisible.value = false
}
const save = (info)  =>{
      request.post("/updateOrder", info).then((res) => {
                        if (res.code === 200) {
                            ElMessage({
                                message: "修改订单成功",
                                type: "success",
                            });
                            getPageList()
                            firstvisible.value = false;
                        } else {
                        ElMessage({
                          message: res.msg,
                          ype: "error",
                      });
                  }
              });
}

// 分页带条件查询所有订单
const getfindNewsPageInfo = (info) => {
  
  return request.post("/findOrderPage", info);
};
//分页查询当前用户自己的订单
const getFindOwnPageInfo = (info) => {
   return request.post("/findUserOrder",info);
}
//删除的回调
// headline/removeByHid
// const removeByHid = (id) => {
//     return request({
//          method: "post",
//             url: "headline/removeByHid",
//             headers: {
//             "Content-Type": "application/x-www-form-urlencoded;charset=utf-8",
//             },
//             data:`hid=${id}`
//   })
// };
// 初始化请求全部订单分页列表数据
const getPageList = async () => {
  let result = await getfindNewsPageInfo(findNewsPageInfo.value);
  console.log(result);
  
  pageData.value = result.data.pageInfo.pageData;
  
  findNewsPageInfo.value.pageNum = result.data.pageInfo.pageNum;
  findNewsPageInfo.value.pageSize = result.data.pageInfo.pageSize;
  totalSize.value = +result.data.pageInfo.totalSize;
};
const getOwnPageList = async() => {
      let result = await getFindOwnPageInfo(findNewsPageInfo.value);
    console.log(result)
    
    pageData.value = result.data.pageInfo.pageData;
    
    findNewsPageInfo.value.pageNum = result.data.pageInfo.pageNum;
    findNewsPageInfo.value.pageSize = result.data.pageInfo.pageSize;
    totalSize.value = +result.data.pageInfo.totalSize;
}
// 组件挂载的生命周期钩子
onMounted(() => {
  identity.value = window.sessionStorage.getItem('identity');
  console.log(identity.value.replace("\"",'').replace("\"",''))
  //const trimmedIdentity = identity.value.replace(/^"|"$/g, '');
  const trimmedIdentity = identity.value.replace("\"",'').replace("\"",'');
  if(trimmedIdentity === 'user'){
    console.log(111)
     getOwnPageList();
  }else if(trimmedIdentity=== 'manager'){
    getPageList();
  }else {
    console.log(22);
  }
  
});
const observe = (item) => {
    firstvisible.value = true;
    console.log(11)
    temppageData.value = item;
    console.log(temppageData.value)
}
// 点击查看全文的回调
// const toDetail = (hid) => {
//   router.push({ name: "Detail" ,query:{ hid }});
// }

// 点击删除的回调
// const handlerDelete = async (id) => {
//   await removeByHid(id)
//   ElMessage.success('删除成功!')
//   //重新获取列表请求
//   getPageList()
// }
//点击修改的回调
// const Modify = (hid) => {
//   router.push({ name: "addOrModifyNews", query: { hid } });
// }
</script>
<style scoped>

.x {
  margin-right: 10px;
  margin-left: 125px;
}
.el-icon {
  margin-right: 100px;
}

.info-item {
  margin-bottom: 20px;
}
</style>