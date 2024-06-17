import request from "@/utils/index";


const {ElMessage} = require("element-plus");
export default {
    name: "login",
    data() {
        return {
            identity: "",
            form: {
                username: "",
                password: "",
                identity: "",
                vercode:"",
                key:"",
            },
            codeimage:"",
            rules: {
                username: [{required: true, message: "请输入账号", trigger: "blur"},],
                password: [{required: true, message: "请输入密码", trigger: "blur"}],
                identity: [{required: true, message: "请选择身份", trigger: "blur"}],
                vercode: [{required: true, message: "请输入验证码", trigger: "blur"}],
            },
        };
    },
    computed: {
        disabled() {
            const {username, password, identity,vercode} = this.form;
            return Boolean(username && password && identity && vercode);
        },
    },
    methods: {
        login() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    console.log(this.form.username)
                    console.log(this.form.password)
                    console.log(this.form.identity)
                    this.identity = this.form.identity;
                    const temp = "/" + this.identity + "/login"
                    console.log(temp)
                    request.post("/" + this.identity + "/login", this.form).then((res) => {
                        if (res.code === 200) {
                            console.log("chengg")
                            ElMessage({
                                message: "登陆成功",
                                type: "success",
                            });
                           // 登陆成功跳转主页
                            window.sessionStorage.setItem("user", JSON.stringify(res.data));
                            window.sessionStorage.setItem("identity", JSON.stringify(this.form.identity));
                            this.$router.replace({path: "/home"});
                          
//   this.$router.push 会向history栈中添加一个新的记录，因此用户可以通过浏览器的后退按钮返回到之前的页面。
// this.$router.replace 会替换当前的history栈中的记录，因此用户无法通过浏览器的后退按钮返回到之前的页面。
// 用途:
// this.$router.push 通常用于实现普通的页面跳转，例如从一个页面跳转到另一个页面。
// this.$router.replace 通常用于实现某些特殊的页面跳转需求，例如在表单提交成功后，将用户重定向到另一个页面，而且用户无法回退到表单提交前的页面
                            console.log(res.data)
                        } else {
                            console.log("fail")
                            console.log(res.message)
                            ElMessage({
                                
                                message: res.message,
                                type: "error",
                            });
                        }
                    });
                }
            });
        },
        regist(){
            this.$router.push("/regist")
        },
        getcode(){
            request.get("/captcha").then((res) => {
                if (res.code === 200) {
                    console.log("chengg")
                    ElMessage({
                        message: "获取验证码成功",
                        type: "success",
                    });
                    //console.log(res.data)
                    this.form.key = res.data.key
                    this.codeimage = res.data.image
                } else {
                    console.log("fail")
                    console.log(res.message)
                    ElMessage({
                        message: res.message,
                        type: "error",
                    });
                }
            });
        }
    },
};