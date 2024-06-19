<template>
  <div style="line-height: 50px;display: flex">
    <div style="width: 200px;margin-left: 10px; font-weight: bold; color: dodgerblue">川の鲜花店</div>
    <Clock style="font-size: 20px;position: absolute;left: 50%;overflow: hidden;"/>
    <div style="flex: 1"></div>
    
  </div>
</template>

<script>

import request from "@/utils/index";
import Clock from "@/components/Clock";
// import { da } from 'element-plus/es/locale';

const {ElMessage} = require("element-plus");
export default {
  name: "HeaderHappy",
  components: {
    Clock
  },
  data() {
    return {
      name: '',
      identify:"",
       status:false,
      form:{
        username:"",
        name:""
      },
        // 定时任务配置
      config: {
        time: '00:00:00', // 每天几点执行
        interval: 1, // 隔几天执行一次
        runNow: false, // 是否立即执行
        intervalTimer: '',
        timeOutTimer: ''
      }
    }
  },
  created() {
         this.load();
  },
  // 一定要记得在组件销毁阶段清除定时器
 beforeUnmount () {
    // 清除任务定时器
    clearInterval(this.config.intervalTimer)
    // 清除定时器timeout
    clearTimeout(this.config.timeOutTimer)
  },
  methods: {
     load() {
            var data = JSON.parse(sessionStorage.getItem("user"));
            console.log(data)
            this.form.username = data.username
            this.form.name = data.name
            this.identify =  JSON.parse(sessionStorage.getItem("identity"));
      },
    SignOut() {
      sessionStorage.clear()
      request.get("/main/signOut");
      ElMessage({
        message: '用户退出登录',
        type: 'success',
      });
      this.$router.replace({path: '/login'});
    },
    selfInfoManage() {
      this.$router.push("/selfInfo")
    },
    signup(){
      if(!this.status){
    console.log(this.form)
        request.post("/" + this.identify +"/signup",this.form).then(res => {
                if (res.code === 200) {
                            console.log("chengg")
                            ElMessage({
                                message: "签到成功",
                                type: "success",
                            });
                             this.status = res.data
                           //签到成功刷新页面
                             this.$router.push("/home")
                        } else {
                            console.log("fail")
                            console.log(res.message)
                            ElMessage({
                                message: res.message,
                                type: "error",
                            });
                        }
        });
      }else {
         alert("签到过了，无需再次签到")
      }
    },
    alluser(){
            this.$router.push("/AllUserInfo")
    },
    selectsign(){
            this.$router.push("/TodaySignUp")
    }
  },
  //定时任务
setTimedTask() {
      // 默认为false，true为立即触发，看你的需求，如果设置为true则立刻运行任务函数
      if (this.config.runNow) {
        this.initBusinessFn()
      }
      // 获取下次要执行的时间，如果执行时间已经过了今天，就让把执行时间设到明天的按时执行的时间
      const nowTime = new Date().getTime()
      const timePoint = this.config.time.split(':').map((i) => parseInt(i))
 
      let recent = new Date().setHours(...timePoint) // 获取执行时间的时间戳
 
      if (recent <= nowTime) {
        recent += 24 * 60 * 60 * 1000
      }
      // 要执行的时间减去现在的时间，就是程序要多少秒之后执行
      const doRunTime = recent - nowTime
      this.config.timeOutTimer = setTimeout(this.setTimer, doRunTime)
 },
 
 
   // 设置定时器
    setTimer () {
      console.log('进入定时器了')
      // 这里是将在你设置的时间点执行你的业务函数
      this.initBusinessFn()
      // 每隔多少天再执行一次，这里设置的是24小时
      const intTime = this.config.interval * 24 * 60 * 60 * 1000
      this.config.intervalTimer = setInterval(this.initBusinessFn, intTime)
    },
 
 
  // 业务函数 
   initBusinessFn() {
      console.log('定时任务函数触')
      this.status = false;
    }
}
</script>

<style scoped>
.right-info {
  width: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 1.5%;
}

.right-info:hover {
  cursor: pointer;
}
</style>