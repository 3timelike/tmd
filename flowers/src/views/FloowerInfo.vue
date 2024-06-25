<template>
    <div class = "fInfo">
        
        <h1 class = "header" align="center" >{{fdata.f_name}}花</h1>
        <hr>
       
        <div class = "photo">
            <el-image v-if = "view" :src="'data:image;base64,' + image" style="width: 400px; height: 400px; border-radius: 40px"/>
            <h1 style="width: 400px; height: 400px ">花语介绍</h1>
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
                     this.init(this.fdata.f_avatar)
                }
                
               
    });
  }
}
</script>
