<template>
  <div class="container">
    <div class="listItem">
      <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>所有信息</el-breadcrumb-item>
      </el-breadcrumb>
      <div class="rightInput" style="margin-right: 50px">
        <el-input v-model.lazy="findNewsPageInfo.keyWords" placeholder="搜索成员"></el-input>
        <!-- <el-button   type="primary">搜索</el-button> -->
      </div>

<!--      -->
      <el-table size="big" current-row-key="id" :data="pageData" stripe highlight-current-row>
        <el-table-column type="index" align="center" label="序号"></el-table-column>
        <el-table-column prop="username" label="用户名" align="center"></el-table-column>
        <el-table-column prop="name" label="姓名" align="center"></el-table-column>
        <el-table-column prop="gender" label="性别" align="center"></el-table-column>
        <el-table-column prop="age" label="年龄" align="center"></el-table-column>
        <el-table-column prop="phone_num" label="手机号" align="center"></el-table-column>
        <el-table-column prop="email" label="邮箱" align="center"></el-table-column>
      </el-table>
<!--      -->
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

const findNewsPageInfo = ref({
  keyWords: "", // 搜索关键字
  pageNum: 1, // 页码数
  pageSize: 5, // 页大小
});
const totalSize = ref(0); //分页总数量
// 初始化列表数据
const pageData = ref([
  {
    username: "",
    name: "",
    gender: "",
    age: "",
    phone_num: "",
    email: "",
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
// 监视初始化参数findNewsPageInfo.value,的变化,当type发生改变的时候重新发送请求获取列表数据
//根据姓名搜索用户
watch(
  () => findNewsPageInfo.value,
  () => {
    console.log("keywordchenge")
    getPageList();
  },
  {
    deep: true,
  }
);

// 分页带条件查询所有人员
const getfindNewsPageInfo = (info) => {

  return request.post("/findNewsPage", info);
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
  let result = await getfindNewsPageInfo(findNewsPageInfo.value);
  console.log(result);
  //pageData.splice(0, pageData.length, ...result.pageInfo.pageData);
  pageData.value = result.data.pageInfo.pageData;
  // alert(this.pageData.value)z
  //console.log(pageData.value)
  findNewsPageInfo.value.pageNum = result.data.pageInfo.pageNum;
  findNewsPageInfo.value.pageSize = result.data.pageInfo.pageSize;
  totalSize.value = +result.data.pageInfo.totalSize;
};
// 组件挂载的生命周期钩子
onMounted(() => {
  getPageList();
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

