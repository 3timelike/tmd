<template>
  <el-menu
      :default-active="this.path"
      router
      style="width: 200px; height:100%; min-height: calc(100vh - 40px)"
      unique-opened
  >
    <!-- <div style="display: flex;align-items: center;justify-content: center;padding: 11px 0;">
      <img alt="" src="@/assets/logo.png" style="width: 60px;">
    </div> -->
    <el-menu-item index="/home">
      <el-icon>
        <house/>
      </el-icon>
      <span>首页</span>

    </el-menu-item>



    <el-sub-menu v-if="this.judgeIdentity()===1" index="2">
      <template #title>
        <el-icon>
          <user/>
        </el-icon>
        <span>成员信息</span>
      </template>
      <el-menu-item  index="/AllUserInfo">员工列表</el-menu-item>
      <el-menu-item  index="/TodaySignUp">今日签到信息</el-menu-item>
    </el-sub-menu>


    <el-sub-menu  index="3">
      <template #title>
        <el-icon>
          <coin/>
        </el-icon>
        <span>鲜花管理</span>
      </template>
      <el-menu-item  index="/floowers">鲜花信息</el-menu-item>
    </el-sub-menu>


    <el-sub-menu  index="4">
      <template #title>
        <el-icon>
          <message/>
        </el-icon>
        <span>订单管理</span>
      </template>
      <el-menu-item v-if="this.judgeIdentity()===1" index="/orderFloower">所有订单信息</el-menu-item>
      <el-menu-item v-if="this.judgeIdentity()===0" index="/orderFloower">我的订单信息</el-menu-item>
      <!-- <el-menu-item v-if="this.judgeIdentity()!==0" index="/repairInfo">退订信息</el-menu-item>
      <el-menu-item v-if="this.judgeIdentity()!==0" index="/repairInfo">销售统计</el-menu-item> -->
    </el-sub-menu>


    


   


    <el-menu-item index="/selfInfo">
      <el-icon>
        <setting/>
      </el-icon>
      <span>个人信息</span>
    </el-menu-item>

  </el-menu>
</template>

<script>


export default {
  name: "AsideHappy",
  data() {
    return {
      user: {},
      identity: '',
      path: this.$route.path
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.identity = window.sessionStorage.getItem('identity')
      // request.get("/main/loadIdentity").then((res) => {
      //   if (res.code !== 200) {
      //     ElMessage({
      //       message: '用户会话过期',
      //       type: 'error',
      //     });
      //     sessionStorage.clear()
      //     request.get("/main/signOut");
      //    }
      //   window.sessionStorage.setItem("identity", JSON.stringify(res.data));
      //   this.identity = res.data
      // });
      // request.get("/main/loadUserInfo").then((result) => {
      //   if (result.code !== "0") {
      //     ElMessage({
      //       message: '用户会话过期',
      //       type: 'error',
      //     });
      //     request.get("/main/signOut");
      //     sessionStorage.clear()
      //     this.$router.replace({path: "/login"});
      //   }
      //   window.sessionStorage.setItem("user", JSON.stringify(result.data));
      //   this.user = result.data
      // });
    },
    judgeIdentity() {
      if (this.identity.replace("\"",'').replace("\"",'') === 'user') {
        return 0
      } else if (this.identity.replace("\"",'').replace("\"",'') === 'manager') {
        return 1
      } else
        return 2
    }
   }
 }
</script>

<style scoped>
.icon {
  margin-right: 6px;
}

.el-sub-menu .el-menu-item {
  height: 50px;
  line-height: 50px;
  padding: 0 45px;
  min-width: 199px;
}
</style>