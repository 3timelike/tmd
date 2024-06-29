<template>
    <div class = "fInfo">
        
        <h1 class = "header" align="center" >{{fdata.f_name}}花</h1>
        <hr>
       
        <div class = "photo">
            <el-image v-if = "view" :src="'data:image;base64,' + image" style="width: 400px; height: 400px; border-radius: 40px"/>
            <h1 style="width: 400px;">鲜花详情</h1>
            <el-collapse v-model="activeNames">
                <el-collapse-item title="鲜花名称" name="1">
                <div style="font-size: 20px;font-weight: 600;">{{ fdata.f_name }}</div>
                </el-collapse-item>
                <el-collapse-item title="鲜花颜色" name="2">
                <div style="font-size: 20px;font-weight: 600;">{{ fdata.f_color}}</div>
                </el-collapse-item>
                <el-collapse-item title="花语介绍" name="4">
                <div style="font-size: 20px;font-weight: 600;">{{  fdata.f_meaning  }}</div>
                </el-collapse-item>
                <el-collapse-item title="花期介绍" name="5">
                <div style="font-size: 20px;font-weight: 600;">{{fdata.f_exist_time }}</div>
                </el-collapse-item>
                <el-collapse-item title="来源地" name="6">
                <div style="font-size: 20px;font-weight: 600;">{{ fdata.f_product_area }}</div>
                </el-collapse-item>
                <el-collapse-item title="味道介绍" name="7">
                <div style="font-size: 20px;font-weight: 600;">{{ fdata.f_aroma }}</div>
                </el-collapse-item>
                <el-collapse-item title="花瓣介绍" name="8">
                <div style="font-size: 20px;font-weight: 600;">{{ fdata.f_petal_num }}</div>
                </el-collapse-item>
                <el-collapse-item title="海拔高度介绍" name="9">
                <div style="font-size: 20px;font-weight: 600;">{{ fdata.f_floower_hign }}</div>
                </el-collapse-item>
                <el-collapse-item v-if="this.judgeIdentit10===1" title="库存" name="10">
                <div style="font-size: 20px;font-weight: 600;">{{ fdata.f_num }}</div>
                </el-collapse-item>
                <el-collapse-item  v-if="this.judgeIdentity===1" title="创建时间" name="11">
                <div style="font-size: 20px;font-weight: 600;">{{ fdata.f_create_date }}</div>
                </el-collapse-item>
            </el-collapse>
        </div>

    </div>
</template>

<script>
import request from '@/utils/index';

import { ElMessage } from 'element-plus';
export default {
    name:"FloowerInfo",
    data(){
        return {
            activeNames:['1'],
            tempid:"",
            image:"",
            view:true,
            fdata: {
                f_id:"",
                f_name: "",
                f_color: "",
                f_meaning: "",
                f_exist_time: "",
                f_product_area: "",
                f_aroma: "",
                f_petal_num:"",
                f_floower_hign:"",
                f_num:0,
                f_avatar:"",
                f_create_date:""
            },
        }
    },
    methods:{
                //鉴别身份
            judgeIdentity() {
            if (this.identity === 'user') {
                return 0
            } else if (this.identity === 'manager') {
                return 1
            } else
                return 2
            },
            async init(data) {
            if (data == null || data == "") {
                console.log("未存图片");
                this.view = false;
            } else {
                
                
                await request.get("/files/initAvatar/" + data).then((res) => {
                    if (res.code === 200) {
                        this.image = res.data.data;
                    } else {
                        ElMessage({
                            message: res.msg,
                            type: "error",
                        });
                    }
                });
            }
        },
    },
    mounted() {
     console.log(this.$route.params.id); // 123
      this.tempid = this.$route.params.id;
       const requestOptions = {
  headers: {
    'Content-Type': 'application/json'
    // 其他自定义头部
  }
};
console.log(typeof(this.tempid))
      request.post("/findFloowerInfoByid",this.tempid,requestOptions).then((res) => {
                //更新fdata
                if(res.code === 200){
                     this.fdata = res.data
                     console.log(res.data);
                     this.init(this.fdata.f_avatar)
                }
                
               
    });
  }
}
</script>