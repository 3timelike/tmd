<template>
    <div class="login-container">
        <!-- <div style="margin-right: 150px; min-width: 300px">
            <img alt="" src="../../public/login_Team.png" style="width: 600px"/>
        </div> -->
        <div style="width: 400px; margin: 1px auto">
            <div style="color: black; font-size: 30px; text-align: left; padding: 30px 0">用户注册</div>

            <el-form ref="form" :model="form" :rules="rules" size="large">
                   <div>
                        <el-upload :on-success="uploadImg" :show-file-list="false"
                              action="http://localhost:9090/files/upload"
                              class="upload-demo"
                        >
                            <div class="AvatarDiv">
                                <img :src="'data:image;base64,' + image" :style="imgDisplay"
                                     style="width: 80px; height: 80px; border-radius: 40px"/>
                                <div class="editImg">
                                    上传头像
                                    <el-icon color="color">
                                        <edit></edit>
                                    </el-icon>
                                </div>
                            </div>
                        </el-upload>
                    </div>
                      
                <el-form-item prop="username" label="账号">
                    <el-input v-model="form.username" clearable prefix-icon="avatar"></el-input>
                </el-form-item>

                <el-form-item prop="password" label="密码 ">
                   <el-input v-model="form.password" prefix-icon="lock" show-password></el-input>
                </el-form-item>

                <el-form-item prop="password2" label="再次确认密码">
                   <el-input v-model="form.password2" prefix-icon="lock" show-password></el-input> 
                </el-form-item>

                <el-form-item prop="name" label="姓名">
                    <el-input v-model="form.name" clearable prefix-icon="avatar"></el-input>
                </el-form-item>

                <el-form-item prop="gender" label="性别">
                    <input type="radio" id="one" value="男" v-model="form.gender" />
                    <label for="one">男</label>

                    <input type="radio" id="two" value="女" v-model="form.gender" />
                    <label for="two">女</label>
                </el-form-item>

                <el-form-item prop="age" label="年龄 ">
                   <el-input v-model.number="form.age" clearable prefix-icon="avatar"></el-input>
                </el-form-item>

                <el-form-item prop="phoneNum" label="电话号码">
                    <el-input v-model="form.phoneNum" prefix-icon="lock" ></el-input>
                </el-form-item>
               <el-form-item prop="email" label="邮箱">
                    <el-input v-model="form.email" prefix-icon="lock" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button :disabled="!disabled" style="width: 20%" type="primary" @click="register">注册</el-button>
                </el-form-item>

            </el-form>
        </div>
    </div>
    
</template>

<script src="@/assets/js/regist.js"></script>


<style scoped>
.login-container {
    position: fixed;
    height: 100%;
    width: 100%;
    top: 0;
    left: 0;
    background: linear-gradient(
            135deg,
            hsl(170deg, 80%, 70%),
            hsl(190deg, 80%, 70%),
            hsl(250deg, 80%, 70%),
            hsl(320deg, 80%, 70%),
            hsl(320deg, 80%, 70%),
            hsl(250deg, 80%, 70%),
            hsl(190deg, 80%, 70%),
            hsl(190deg, 80%, 70%),
            hsl(170deg, 80%, 70%)
    );
    background-size: 600%;
    animation: myanimation 15s linear infinite;
}

@keyframes myanimation {
    0% {
        background-position: 0 0;
    }
    100% {
        background-position: 100% 100%;
    }
}

.footer {
    left: 0;
    bottom: 0;
    color: #fff;
    width: 100%;
    position: absolute;
    text-align: center;
    line-height: 30px;
    padding-bottom: 10px;
    text-shadow: #000 0.1em 0.1em 0.1em;
    font-size: 14px;
}

.footer a,
.footer span {
    color: #fff;
}
</style>
