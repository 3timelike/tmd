<template>
    <div class = "fPileInfo">
        
        <h1 class = "header" align="center" style="font-weight: 1000;">{{flowerPile.name}}</h1>
        <hr>
       
        <div class = "photo">
            <el-image v-if = "view" :src="'data:image;base64,' + image" style="width: 400px; height: 400px; border-radius: 40px"/>
            <h1 style="width: 400px; ">组合鲜花</h1>
            <el-table :data="flowerPile.flowers" style="width: 100%">
          <el-table-column label="序号" prop="f_id"></el-table-column>
          <el-table-column label="花名" prop="f_name"></el-table-column>
          <el-table-colum  label="颜色" prop="f_color"></el-table-colum>
          <el-table-column label="花语" prop="f_meaning"></el-table-column>
          <el-table-column label="花期" prop="f_exist_time"></el-table-column>
          <el-table-column label="产地" prop="f_product_area"></el-table-column>
          <el-table-column label="香味" prop="f_aroma"></el-table-column>
          <el-table-column label="花瓣" prop="f_petal_num"></el-table-column>
          <el-table-column label="高度" prop="f_floower_hign"></el-table-column>
          <el-table-column v-if="this.judgeIdentity()===1" label="库存" prop="f_num"></el-table-column>
        </el-table>
        </div>
    </div>
</template>

<script >
import request from '@/utils/index';

import { ElMessage } from 'element-plus';
export default {
    name:"FloowerPileInfo",
    data(){
        return {
            tempid:"",
            image:"",
            view:true,
            flowerPile: {
                id:"",
                name: "",
                price:"",
                amount:"",
                avatar:"",
                flowers: [{
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
            }],
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
      request.post("/findFloowerPileInfoByid",this.tempid,requestOptions).then((res) => {
                //更新fdata
                if(res.code === 200){
                     this.flowerPile = res.data
                     console.log(res.data);
                     this.init(this.flowerPile.avatar)
                }
    });
  }
}
</script>
