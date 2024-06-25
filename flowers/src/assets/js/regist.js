import request from "@/utils/index";

const {ElMessage} = require("element-plus");
export default {
    name: "regist",
    data() {
        // 手机号验证
        const checkPhone = (rule, value, callback) => {
            const phoneReg = /^1[3|4|5|6|7|8][0-9]{9}$/;
            if (!value) {
                return callback(new Error("电话号码不能为空"));
            }
            setTimeout(() => {
                if (!Number.isInteger(+value)) {
                    callback(new Error("请输入数字值"));
                } else {
                    if (phoneReg.test(value)) {
                        callback();
                    } else {
                        callback(new Error("电话号码格式不正确"));
                    }
                }
            }, 100);
        };
        const password2 = (rule, value, callback) => {
            
                console.log("验证");
                if (value == "") {
                    callback(new Error("请再次输入密码"));
                } else if (value !== this.form.password) {
                    callback(new Error("两次输入密码不一致!"));
                } else {
                    callback();
                }
        };
        return {
            showpassword: true,
            image:"",
            form: {
                username: "",
                password: "",
                password2: "",
                name:"",
                gender:"",
                age:"",
                phoneNum:"",
                email:"",
                avatar:""
            },
            rules: {
                username: [
                    {required: true, message: "请输入账号", trigger: "blur"},
                    {
                        pattern: /^[a-zA-Z0-9]{4,9}$/,
                        message: "必须由 4 到 9 个字母或数字组成",
                        trigger: "blur",
                    },
                ],
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
                        pattern: /^([1-9]){1,2}$/,
                        message: "范围：1-100",
                        trigger: "blur",
                    },
                ],
                phoneNum: [{required: true, validator: checkPhone, trigger: "blur"}],
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
                password2: [{validator: password2, trigger: "blur"}],
                
            },
        };
    },
    computed: {
        disabled() {
            const {username, password,name,gender,age,phoneNum,email} = this.form;
            return Boolean(username && password && name && gender && age && phoneNum && email);
        },
    },
    methods: {
        register() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    request.post("/regist", this.form).then((res) => {
                        if (res.code === 200){
                            ElMessage({
                                message: "注册成功",
                                type: "success",
                        });
                           //注册成功跳转主页
                            
                            window.sessionStorage.setItem("user", JSON.stringify(res.data));
                            window.sessionStorage.setItem("identity", JSON.stringify("user"));
                            console.log(sessionStorage.getItem("identity"))
                            this.$router.replace({path: "/home"});
                        } else {
                            ElMessage({
                                message: res.message,
                                type: "error",
                            });
                        }
                    });
                }else{
                    alert("输入信息有误")
                }
            });
        },
        async init(data) {
            if (data === null || data === "") {
                this.imgDisplay = {display: "none"};
            } else {
                this.imgDisplay = {display: "block"};
                console.log("头像名称：" + data);
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
        
    },
};