<template>
  <div class="container">
    <div class="listItem">
      <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>今日签到记录</el-breadcrumb-item>
      </el-breadcrumb>
    <div class="rightInput" style="margin-right: 50px">
        <el-input v-model.lazy="keywords" placeholder="搜索成员"></el-input>
        <!-- <el-button   type="primary">搜索</el-button> -->
    </div>
    <div class="info-item" style="margin-right: 50px">
        <span class="el-dropdown-link">
          <el-icon :size="18" style="float: center;margin-right: 7px;"><avatar/></el-icon>
                    今日已签到
          <el-icon class="el-icon--right"><arrow-down/></el-icon><br>
        </span>
        
    </div>
      <!-- 已签到列表 -->
      <div >
        <el-table :data="pageData" style="width: 100%">
      <el-table-column label="用户名" prop="sign_username"></el-table-column>
      <el-table-column label="姓名" prop="sign_name"></el-table-column>
      <el-table-column label="性别" prop="sign_data"></el-table-column>
      <el-table-column label="年龄" prop="sign_time"></el-table-column>
      
    </el-table>
      </div>
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
      <div class="info-item" style="margin-right: 50px">
        <span class="el-dropdown-link">
          <el-icon :size="18" style="float: center;margin-right: 7px;"><avatar/></el-icon>
          今日未签到
          <el-icon class="el-icon--right"><arrow-down/></el-icon>
        </span>
    </div>
       <!-- 未签到列表 -->
      <div>
       <el-table :data="pageData1" style="width: 100%">
      <el-table-column label="用户名" prop="username"></el-table-column>
      <el-table-column label="姓名" prop="name"></el-table-column>
      
    </el-table>
      </div>
      <!-- 分页器 -->
      <div style="margin-top: 20px">
        <el-pagination
          v-model:current-page="findNewsPageInfo1.pageNum"
          v-model:page-size="findNewsPageInfo1.pageSize"
          @size-change="getPageList1"
          @current-change="getPageList1"
          :page-sizes="[5, 7, 10]"
          background
          layout="prev, pager, next , ->, sizes, total"
          :total="totalSize1"
        />
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
// import { ElMessage } from 'element-plus'

const keywords = ref("")
const findNewsPageInfo = ref({
    keyWords:"",
  pageNum: 1, // 页码数
  pageSize: 5, // 页大小
});
const totalSize = ref(0); //分页总数量
// 初始化列表数据
const pageData = ref([
  {
    sign_username: "",
    sign_name:"",
    sign_data:"",
    sign_time:""
  },
]);
const findNewsPageInfo1 = ref({
    keyWords:"",
  pageNum: 1, // 页码数
  pageSize: 5, // 页大小
});
const totalSize1 = ref(0); //分页总数量
// 初始化列表数据
const pageData1 = ref([
  {
    username: "",
    name:""
  },
]);
//接收header组件用户搜索的数据
// Bus.on('keyword', (keywords) => {
//   findNewsPageInfo.value.keyWords = keywords
// })
// header点击切换高亮的时候传递过来的tid
// Bus.on('tid', (type) => {
//   findNewsPageInfo.value.type = type
// })
// 监视初始化参数keywords的变化,当type发生改变的时候重新发送请求获取列表数据
watch(
  () => keywords,
  () => {
    findNewsPageInfo1.value.keyWords = keywords;
    findNewsPageInfo.value.keyWords = keywords;
    getPageList();
    getPageList1();
  },
  {
    deep: true,
  }
);
//检测搜索输入
watch(findNewsPageInfo.value.keyWords, (newVal) => {
  this.findNewsPageInfo.value.keyWords = newVal;
});
// 分页带条件查询所有签到人员
const getfindSingUpPageInfo = (info) => {
  return request.post("/findSignUpPage", info);
};
// 分页带条件查询所有未签到人员
const getfindSingUpPageInfo1 = (info) => {
  return request.post("/findNoSignUpPage", info);
};
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
// 初始化请求分页列表数据
const getPageList = async () => {
  let result = await getfindSingUpPageInfo(findNewsPageInfo.value);
  console.log(result);
  //pageData.splice(0, pageData.length, ...result.pageInfo.pageData);
  pageData.value = result.data.pageInfo.pageData;
  // alert(this.pageData.value)
  //console.log(pageData.value)
  findNewsPageInfo.value.pageNum = result.data.pageInfo.pageNum;
  findNewsPageInfo.value.pageSize = result.data.pageInfo.pageSize;
  totalSize.value = +result.data.pageInfo.totalSize;
};
const getPageList1 = async () => {
  let result = await getfindSingUpPageInfo1(findNewsPageInfo1.value);
  console.log(result);
  //pageData.splice(0, pageData.length, ...result.pageInfo.pageData);
  pageData1.value = result.data.pageInfo.pageData;
  // alert(this.pageData.value)
  //console.log(pageData.value)
  findNewsPageInfo1.value.pageNum = result.data.pageInfo.pageNum;
  findNewsPageInfo1.value.pageSize = result.data.pageInfo.pageSize;
  totalSize1.value = +result.data.pageInfo.totalSize;
};
// 组件挂载的生命周期钩子
onMounted(() => {
  getPageList();
  getPageList1();
});
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
  margin-right: 5px;
  margin-left: 125px;
}
.el-icon {
  margin-right: 100px;
}

.info-item {
  margin-bottom: 20px;
}
</style>