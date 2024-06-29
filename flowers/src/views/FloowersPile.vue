<template>
    <div class="container">
    <div class="listItem">
      <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>鲜花信息</el-breadcrumb-item>
      </el-breadcrumb>
      <div class="rightInput" style="margin-right: 50px">
        <el-input v-model.lazy="findNewsPageInfo.keyWords" placeholder="搜索花堆"></el-input>
      
        <el-tooltip v-if="this.judgeIdentity()===1" content="添加" placement="top">
                <el-button icon="plus" style="width: 50px" @click="Edit">添加</el-button>
        </el-tooltip>
            
      </div>
    
       <el-table :data="fdata" style="width: 100%">
      <el-table-column v-if="false" label="序号" prop="f_id"></el-table-column>
      <el-table-column label="组合名称" prop="name"></el-table-column>
      <el-table-column  label="价格" prop="price"></el-table-column>
      <el-table-column label="库存" prop="amount"></el-table-column>
      <el-table-column v-if="false"  label="上传时间" prop="f_create_date"></el-table-column>
      <el-table-column v-if="false" label="文件名" prop="f_avatar"></el-table-column>

      <el-table-column  label = "" >
      <template  v-slot="scope">
              <el-button icon="plus" style="width: 50px" @click="FloowerInfo(scope.row)">详情信息</el-button>
      </template>
      </el-table-column >

       <el-table-column  label = "" >
      <template v-if="this.judgeIdentity()===1" v-slot="scope">
              <el-button icon="plus" style="width: 50px" @click="update(scope.row)">修改</el-button>
      </template>
      </el-table-column >

       <el-table-column  label = "">
      <template v-if="this.judgeIdentity()===1" v-slot="scope">
              <el-button icon="plus" style="width: 50px" @click="remove(scope.row)">删除</el-button>
      </template>
            </el-table-column>


            <el-table-column  label = "操作" >
        
      
      <template v-slot="scope">
              <el-button  style="width: 50px" @click="buyFloower(scope.row)">购买</el-button>
      </template>
            </el-table-column>
       
    </el-table>
      <!-- 列表 -->
      <!-- <div class="containerItem" v-for="item in fdata" :key="item">
        <div>
            <el-tooltip content="修改鲜花信息" placement="top">
                <el-button icon="plus" style="width: 50px" @click="update(item)"></el-button>
        </el-tooltip>

        <el-tooltip content="删除此鲜花" placement="top">
                <el-button icon="plus" style="width: 50px" @click="remove(item)"></el-button>
        </el-tooltip>

         <el-tooltip  content="购买此鲜花" placement="top">
                <el-button icon="plus" style="width: 50px" @click="buyFloower(item)"></el-button>
                </el-tooltip>  
          </div>
      </div>         -->
        
          
        

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
                <!--      添加信息弹窗  -->
                <el-dialog v-model="dialogVisible" title="添加操作" width="30%" @close="cancel">

                    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
                       <div>
                        <el-upload :on-success="uploadImg" :show-file-list="false"
                              action="http://localhost:9090/files/upload"
                              class="upload-demo"
                        >
                            <div class="AvatarDiv">
                                <img :src="'data:image;base64,' + image" :style="imgDisplay"
                                     style="width: 80px; height: 80px; border-radius: 40px"/>
                                <div class="editImg">
                                    上传图片
                                    <el-icon color="color">
                                        <edit></edit>
                                    </el-icon>
                                </div>
                            </div>
                        </el-upload>
                    </div>
                        <el-form-item label="花堆名" >
                            <el-input v-model="form.name"  style="width: 80%"></el-input>
                        </el-form-item>
                        <el-form-item label="价格" >
                            <el-input v-model="form.price"  style="width: 80%"></el-input>
                        </el-form-item>
                        <el-form-item label="库存" >
                            <el-input v-model="form.amount" style="width: 80%"></el-input>
                        </el-form-item>
                        
                        <el-form-item>
                            <el-select placeholder="组合鲜花" multiple v-model="form.flowers" style="width: 200px;" class="filter-item">
                                <el-option v-for="item in flowersList" :value="item.f_id" :label="item.f_name" :key="item.f_id"></el-option>
                            </el-select>
                        </el-form-item>
                        

                    </el-form>
                    <template #footer>
            <span class="dialog-footer">
              <el-button @click="cancel">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </span>
                    </template>
                </el-dialog>
            </div>
            <div>
                <!--      更改鲜花信息弹窗  -->
                <el-dialog v-model="secondvisible" title="更改操作" width="30%" @close="cancel1">
                      
                    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
                               
                                <el-upload :on-success="uploadSuccess" :show-file-list="false"
                                   action="http://localhost:9090/files/upload"
                                   class="upload-demo"
                                >
                           <div class="AvatarDiv" >
                                <img :src="'data:image;base64,' + image" :style="imgDisplay"
                                     style="width: 80px; height: 80px; border-radius: 40px"/>
                                <div class="editImg">
                                    相关照片
                                    <el-icon color="color">
                                        <edit></edit>
                                    </el-icon>
                                </div>
                            </div>
                               </el-upload>
                          <el-form-item label="花的序号" >
                            <el-input v-model="form.id"  disabled style="width: 80%"></el-input>
                        </el-form-item> 

                        <el-form-item label="花堆名" >
                            <el-input v-model="form.name"   style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="价格" >
                            <el-input v-model="form.price"   style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="库存" >
                            <el-input v-model="form.amount" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-select placeholder="组合鲜花" multiple v-model="form.flowers" style="width: 200px;" class="filter-item">
                                <el-option v-for="item in flowersList" :value="item.f_id" :label="item.f_name" :key="item.f_id"></el-option>
                            </el-select>
                        </el-form-item>

                    </el-form>
                    <template #footer>
            <span class="dialog-footer">
              <el-button @click="cancel1">取 消</el-button>
              <el-button type="primary" @click="save1">确 定</el-button>
            </span>
                    </template>
                </el-dialog>
            </div>
            <div>
                <!--      购买鲜花弹窗  -->
                <el-dialog v-model="threevisible" title="购买操作" width="30%" @close="cancel2">

                    <el-form ref="buyFdata" :model="buyFdata" :rules="rules" label-width="120px">

                          

                        <el-form-item label="用户账号注册时电话" >
                            <el-input v-model="buyFdata.phone_num" disabled style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item  label="本次购买预留电话号" >
                            <el-input v-model="buyFdata.o_phone_num" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="客户地址" >
                            <el-input v-model="buyFdata.o_area" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="备注" >
                           <el-input v-model="buyFdata.o_note" style="width: 80%"></el-input>
                            
                        </el-form-item>

                  </el-form>
                    <template #footer>
            <span class="dialog-footer">
              <el-button @click="cancel2">取 消</el-button>
              <el-button type="primary" @click="save2">确 定</el-button>
            </span>
                    </template>
                </el-dialog>
            </div>
    </div>
  </div>
</template>
<script >
import request from "@/utils/index";



const {ElMessage} = require("element-plus");
export default {
    name: "FloowersInfo",
    data() {
        return {
            showpassword: true,
            image: "",
            //tempimage: "",
            editJudge: true,
            disabled: true,
            dialogVisible: false,
            secondvisible:false,
            threevisible:false,
            identity: "",
            //targetURL: "",
            //avatar: "",
            
            findNewsPageInfo : {
                keyWords: "", // 搜索关键字
                pageNum: 1, // 页码数
                pageSize: 5, // 页大小
            },
            totalSize : 0, //分页总数量
            fdata: [{
                id:"",
                name: "",
                price:"",
                amount:"",
                avatar:"",
                flowers:[]
            }],
            flowersList: {
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
            form: {
                id:"",
                name: "",
                price:"",
                amount:"",
                avatar:"",
                flowers:[]
            },
            buyFdata:{
                f_id:"",
                u_id:"",
                o_phone_num:"",
                phone_num:"",
                o_area:"",
                o_note:"",
                f_name:""
            },
            

            rules: {
                name: [
                    {required: true, message: "请输入姓名", trigger: "blur"},
                    {
                        pattern: /^(?:[\u4E00-\u9FA5·]{2,10})$/,
                        message: "必须由 2 到 10 个汉字组成",
                        trigger: "blur",
                    },
                ],
                gender: [{required: true, message: "请选择性别", trigger: "change"}],
                age: [
                    {required: true, message: "请输入年龄", trigger: "blur"},
                    {type: "number", message: "年龄必须为数字值", trigger: "blur"},
                    {
                        pattern: /^(1|[1-9]\d?|100)$/,
                        message: "范围：1-100",
                        trigger: "blur",
                    },
                ],
               
                email: [
                    {type: "email", message: "请输入正确的邮箱地址", trigger: "blur"},
                ],
                password: [
                    {required: true, message: "请输入密码", trigger: "blur"},
                    {
                        min: 6,
                        max: 32,
                        message: "长度在 6 到 16 个字符",
                        trigger: "blur",
                    },
                ],
            },
            display: {
                display: "none",
            },
            imgDisplay: {
                display: "none",
            },
        };
    },
    created() {
        this.load();
        this.find();
        this.getAllFlowers();
    },
watch: {
  'findNewsPageInfo': {
    handler(newValue, oldValue) {
      // 在这里编写当 findNewsPageInfo.keyWords 变化时需要执行的逻辑
      console.log('findNewsPageInfo.keyWords 变化了', newValue, oldValue);
      this.find();
    },
    deep: true // 如果 findNewsPageInfo 是一个对象，需要设置 deep 为 true 来深度监听对象内部属性的变化
  }
},
    methods: {
        // 获取所有的鲜花
        getAllFlowers(){
            request.post("/findAllFloowers",this.findNewsPageInfo).then((res) => {
                this.flowersList = res.data.pageInfo.pageData
            });
        },
        FloowerInfo(info){
            const path = "/floowerPileInfo/" + info.id;
            this.$router.push({path: path})
            console.log(info);
        },
        //鉴别身份
    judgeIdentity() {
      if (this.identity === 'user') {
        return 0
      } else if (this.identity === 'manager') {
        return 1
      } else
        return 2
    },
        //查询数据，
        find() {
            request.post("/flowerPile/findAllFlowerPile",this.findNewsPageInfo).then((res) => {
                //更新fdata
                this.findNewsPageInfo.pageNum = res.data.pageInfo.pageNum;
                this.findNewsPageInfo.pageSize = res.data.pageInfo.pageSize;
                this.totalSize = res.data.pageInfo.totalSize;
                this.fdata = res.data.pageInfo.pageData
                
            });
        },
        load() {
            this.identity = JSON.parse(sessionStorage.getItem("identity"));
        },
        Edit() {
            this.dialogVisible = true;
            this.form = {form: "none"};
            // this.$nextTick(() => {
            //     this.$refs.form.resetFields();
            //     // this.form = JSON.parse(sessionStorage.getItem("user"));
            // });
        },
        cancel() {
            //this.$refs.form.resetFields();
            //this.$refs.fdata.resetFields();
            this.display = {display: "none"};
            this.showpassword = true;
            this.editJudge = true;
            this.disabled = true;
            this.dialogVisible = false;
           
        },
        cancel1() {
            //this.$refs.form.resetFields();
            //this.$refs.fdata.resetFields();
            
            this.display = {display: "none"};
            this.showpassword = true;
            this.editJudge = true;
            this.disabled = true;
            this.secondvisible = false;
             this.image = "";
        },
        async save() {
            this.$refs.form.validate(async (valid) => {
                if (valid) {
                    //添加
                    await request.put("/" + this.identity + "/addNewFloowerPile", this.form).then((res) => {
                        if (res.code === 200) {
                            ElMessage({
                                message: "添加成功",
                                type: "success",
                            });
                            this.find();
                            this.dialogVisible = false;
                            
                        } else {
                            ElMessage({
                                message: res.msg,
                                type: "error",
                            });
                        }
                    });
                }
            });
        },
        update(row){
            
           this.form = row
            
           this.init(this.form.avatar)

           this.secondvisible = true;
           
        },
        remove(item){
            console.log(item);
         request.post("/" + this.identity + "/removeFloowerPile",item).then((res) => {
                if (res.code === 200) {
                            ElMessage({
                                message: "删除成功",
                                type: "success",
                });
                            
                            this.find();
                            
                } else {
                    ElMessage({
                        message: res.msg,
                        type: "error",
                    });
                }
                
            });
           
        },
        async save1() {
            this.$refs.form.validate(async (valid) => {
                if (valid) {
                    //修改
                    console.log(this.form)
                    await request.post("/" + this.identity + "/updateFloowerPile", this.form).then((res) => {
                        if (res.code === 200) {
                            ElMessage({
                                message: "修改成功",
                                type: "success",
                            });
                            this.find();
                            this.secondvisible = false;
                        } else {
                            ElMessage({
                                message: res.msg,
                                type: "error",
                            });
                        }
                    });
                }
            });
             this.image = "";
        },

        buyFloower(item){
            if(item.amount >= 1){
            this.buyFdata.f_id = item.id
            this.buyFdata.f_name = item.name
            this.buyFdata.u_id = JSON.parse(sessionStorage.getItem("user")).uid;
            this.buyFdata.phone_num = JSON.parse(sessionStorage.getItem("user")).phoneNum;
            this.threevisible = true;
            }else {
                alert("库存不足")
            }
            
        },
        cancel2() {
            //this.$refs.form.resetFields();
            //this.$refs.fdata.resetFields();
            this.display = {display: "none"};
            // this.showpassword = true;
            this.editJudge = true;
            this.disabled = true;
            this.threevisible = false;
        },
        async save2() {
            this.$refs.buyFdata.validate(async (valid) => {
                if (valid) {
                    //购买
                    console.log(this.buyFdata)
                    await request.post("/flowerPile/buyFlowerPile", this.buyFdata).then((res) => {
                        if (res.code === 200) {
                            ElMessage({
                                message: "购买成功",
                                type: "success",
                            });
                            this.secondvisible = false;
                            this.$router.push("/OrderFloower")
                        } else {
                            ElMessage({
                                message: res.msg,
                                type: "error",
                            });
                        }
                    });
                }
            });
        },
        async init(data) {
            if (data == null || data == "") {
                console.log("未存图片");
                this.imgDisplay = {display: "none"};
                
            } else {
                this.imgDisplay = {display: "block"};
                
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
         async uploadImg() {
            await request.get("/files/getavatarname").then((res) => {
                if (res.code === 200) {
                    ElMessage({
                        message: "获取头像名成功",
                        type: "success",
                    });
                    //获取头像文件名
                    this.form.avatar = res.data;
                    console.log("上传成功：" + this.avatar);
                    this.init(res.data)
                } else {
                    ElMessage({
                        message: res.msg,
                        type: "error",
                    });
                }
            });
        },
        async uploadSuccess() {
            // JSON.stringify(this.queryParam),
            const requestOptions = {
  headers: {
    'Content-Type': 'application/json'
    // 其他自定义头部
  }
};
            console.log(this.form)
            await request.post("/files/uploadFloowerAvatar", this.form.id,requestOptions).then((res) => {
                if (res.code === 200) {
                    ElMessage({
                        message: "设置成功",
                        type: "success",
                    });
                    //获取头像文件名
                    this.form.avatar= res.data;
                    console.log("上传成功：" + this.avatar);
                    this.init(this.form.avatar);
                } else {
                    ElMessage({
                        message: res.msg,
                        type: "error",
                    });
                }
            });
        },
    },
};
</script>

<style>
 .list-content {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  overflow: hidden;
}
 .list-content .list-item {
  transition: all 0.5s;
  flex-shrink: 0;
  width: 234px;
  height: 339px;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #000;
  background-color: #fff;
  margin-bottom: 10px;
}
</style>