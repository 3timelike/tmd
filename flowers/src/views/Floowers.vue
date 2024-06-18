<template>
    <div class="container">
    <div class="listItem">
      <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>鲜花信息</el-breadcrumb-item>
      </el-breadcrumb>
      <div class="rightInput" style="margin-right: 50px">
        <el-input v-model.lazy="findNewsPageInfo.keyWords" placeholder="搜索鲜花"></el-input> 
      
        <el-tooltip content="添加" placement="top">
                <el-button icon="plus" style="width: 50px" @click="Edit">添加</el-button>
        </el-tooltip>
      </div>

      <div>
        <el-table size="big" current-row-key="id" :data="fdata" stripe highlight-current-row>
          <el-table-column type="index" align="center" label="序号"></el-table-column>
          <el-table-column prop="f_name" label="花名" align="center"></el-table-column>
          <el-table-column prop="f_color" label="颜色" align="center"></el-table-column>
          <el-table-column prop="f_meaning" label="含义" align="center"></el-table-column>
          <el-table-column prop="f_exist_time" label="花期" align="center"></el-table-column>
          <el-table-column prop="f_product_area" label="产地" align="center"></el-table-column>
          <el-table-column prop="f_aroma" label="香味" align="center"></el-table-column>
          <el-table-column prop="f_petal_num" label="花瓣" align="center"></el-table-column>
          <el-table-column prop="f_floower_hign" label="海拔" align="center"></el-table-column>
          <el-table-column label="操作">
            <el-tooltip content="修改鲜花信息" placement="top">
              <el-button icon="plus" style="width: 50px" @click="update(row)">修改</el-button>
            </el-tooltip>

            <el-tooltip content="删除此鲜花" placement="top">
              <el-button icon="plus" style="width: 50px" @click="remove(row)">删除</el-button>
            </el-tooltip>


            <el-tooltip  content="购买此鲜花" placement="top">
              <el-button  style="width: 50px" @click="buyFloower(row)">购买</el-button>
            </el-tooltip>
          </el-table-column>
        </el-table>
      </div>
      <!-- 列表 -->
<!--      <div class="containerItem" v-for="item in fdata" :key="item">-->
<!--        <div>-->
<!--            <span class="x">{{ item.f_id }}</span>-->
<!--          <span class="x">{{ item.f_name }}</span>-->
<!--          <span class="x">{{ item.f_num }}</span>-->

<!--        </div>-->
<!--      </div>-->


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

                        <el-form-item label="花名" >
                            <el-input v-model="form.f_name"  style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="颜色" >
                            <el-input v-model="form.f_color"  style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item  label="花语介绍" >
                            <el-input v-model="form.f_meaning" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="花期介绍" >
                            <el-input v-model="form.f_exist_time" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="产地" >
                           <el-input v-model="form.f_product_area" style="width: 80%"></el-input>
                            
                        </el-form-item>

                        <el-form-item label="味道介绍" >
                            <el-input v-model.number="form.f_aroma" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="花瓣介绍" >
                            <el-input v-model.number="form.f_petal_num" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="高度介绍" >
                            <el-input v-model="form.f_floower_hign" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="库存" >
                            <el-input v-model="form.f_num" style="width: 80%"></el-input>
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

                    <el-form ref="tempfdata" :model="tempfdata" :rules="rules" label-width="120px">

                          <el-form-item label="花的id" >
                            <el-input v-model="tempfdata.f_id"  disabled style="width: 80%"></el-input>
                        </el-form-item> 

                        <el-form-item label="花名" >
                            <el-input v-model="tempfdata.f_name"   style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="颜色" >
                            <el-input v-model="tempfdata.f_color"  style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item  label="花语介绍" >
                            <el-input v-model="tempfdata.f_meaning" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="花期介绍" >
                            <el-input v-model="tempfdata.f_exist_time" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="产地" >
                           <el-input v-model="tempfdata.f_product_area" style="width: 80%"></el-input>
                            
                        </el-form-item>

                        <el-form-item label="味道介绍" >
                            <el-input v-model.number="tempfdata.f_aroma" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="花瓣介绍" >
                            <el-input v-model.number="tempfdata.f_petal_num" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="高度介绍" >
                            <el-input v-model="tempfdata.f_floower_hign" style="width: 80%"></el-input>
                        </el-form-item>

                        <el-form-item label="库存" >
                            <el-input v-model="tempfdata.f_num" style="width: 80%"></el-input>
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
            fdata: {
                f_id:"",
                f_name: "",
                f_color: "",
                f_meaning: "",
                f_exist_time: "",
                f_product_area: "",
                f_aroma: "",
                f_petal_num:0,
                f_floower_hign:0,
                f_num:0,
                f_create_date:""
            },
            tempfdata: {
                f_id:"",
                f_name: "",
                f_color: "",
                f_meaning: "",
                f_exist_time: "",
                f_product_area: "",
                f_aroma: "",
                f_petal_num:0,
                f_floower_hign:0,
                f_num:0,
                f_create_date:"",
                f_avatar:""
            },
            form: {
                f_name: "",
                f_color: "",
                f_meaning: "",
                f_exist_time: "",
                f_product_area: "",
                f_aroma: "",
                f_petal_num:0,
                f_floower_hign:0,
                f_num:0,
                f_avatar:""
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
        
    },
watch: {
  'findNewsPageInfo.keyWords': {
    handler(newValue, oldValue) {
      // 在这里编写当 findNewsPageInfo.keyWords 变化时需要执行的逻辑
      console.log('findNewsPageInfo.keyWords 变化了', newValue, oldValue);
      this.find();
    },
    deep: true // 如果 findNewsPageInfo 是一个对象，需要设置 deep 为 true 来深度监听对象内部属性的变化
  }
},
    methods: {
        //查询数据，
        find() {
            request.post("/" + this.identity + "/findAllFloowers",this.findNewsPageInfo).then((res) => {
                //更新fdata
                this.fdata =  res.data.pageInfo.pageData
                this.findNewsPageInfo.pageNum = res.data.pageInfo.pageNum;
                this.findNewsPageInfo.pageSize = res.data.pageInfo.pageSize;
                this.totalSize = +res.data.pageInfo.totalSize;
            });
        },
        load() {
            this.identity = JSON.parse(sessionStorage.getItem("identity"));
        },
        Edit() {
            this.dialogVisible = true;
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
        },
        async save() {
            this.$refs.form.validate(async (valid) => {
                if (valid) {
                    //添加
                    await request.put("/" + this.identity + "/addNewFloower", this.form).then((res) => {
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
        update(item){
            this.tempfdata = item
           this.secondvisible = true;
        },
        remove(item){
         request.post("/" + this.identity + "/removeFloower",item).then((res) => {
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
            this.$refs.tempfdata.validate(async (valid) => {
                if (valid) {
                    //修改
                    console.log(this.tempfdata)
                    await request.post("/" + this.identity + "/updateFloower", this.tempfdata).then((res) => {
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
        },

        buyFloower(item){
            if(item.f_num >= 1){
            this.buyFdata.f_id = item.f_id
            this.buyFdata.f_name = item.f_name
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
                    await request.post("/buyFloower", this.buyFdata).then((res) => {
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
        
    },
};
</script>

