<template>
            <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>个人信息</el-breadcrumb-item>
        </el-breadcrumb>
        <h1 style="margin-left: 80px;">留言板</h1>
        <el-input v-model.lazy="PageInfo.keyWords" label="搜索鲜花"></el-input>
        <hr>
        <!--   -->
        <!-- 内容区 -->
        <div style="" >
            <el-table :data="messages" style="width: 100%">
            <el-table-column label="头像" prop="image" > 
                 <template v-slot="scope">
                <img v-if ="scope.row.image" :src="'data:image;base64,' + scope.row.image" style="width: 60px">
                 </template>
            </el-table-column>
            <el-table-column label="用户名" prop="name"></el-table-column>
            <el-table-column label="内容" prop="content"></el-table-column>
            <el-table-column label="发送时间" prop="send_date"></el-table-column>

            <el-table-column>
            <template  v-if="this.judgeIdentity()===1" v-slot="scope">
                    <el-button icon="plus" style="width: 50px" @click="deleteMessage(scope.row)">删除评论</el-button>
            </template>
            </el-table-column >
            </el-table>
        </div>

            <!-- 分页组件--> 
                <div style="margin-top: 20px">
        <el-pagination
          v-model:current-page="PageInfo.pageNum"
          v-model:page-size="PageInfo.pageSize"
          @size-change="getAll"
          @current-change="getAll"
          :page-sizes="[3,5]"
          background
          layout="prev, pager, next , ->, sizes, total"
          :total="totalSize"
        />
      </div>
            <!-- 底部 -->
                <div class="input-content">
                    <el-input
                    type="textarea"
                    placeholder="发表评论"
                    v-model="form.content"
                    maxlength="100"
                    show-word-limit
                    style="width: 500px;border: 1px 1px solid black;"
                    >
                </el-input>
                    <el-button style="width: 50px;" @click="send()">发送</el-button>
                </div>
</template>

<script>
    import request from '@/utils/index';
   
    export default{
        name:"LeaveMessage",
        data() {
            return {
                identity:"",
                
                form:{
                    from:"",
                    content:"",
                    sendDate:""   
                },
                messages:[{
                  id:"",
                  name:"",
                  avatar:"",
                  content:"",
                  send_date:"",
                  image:"",
                  }],
                PageInfo : {
                    keyWords: "", // 搜索关键字
                    pageNum: 1, // 页码数
                    pageSize: 5, // 页大小
                },
                totalSize : 0, //分页总数量
            }
        },
        created() {
            this.getAll();
            this.load();
        },
          watch: {
            PageInfo: {
              handler() {
               console.log(this.PageInfo)
               this.getAll();
             },
            deep: true // 深度监听，可以监视对象内部数据的变化
            }

  },
        methods: {
            judgeIdentity() {
                if (this.identity === 'user') {
                    return 0
                } else if (this.identity === 'manager') {
                    return 1
                } else
                    return 2
                },
                load() {
                    this.identity = JSON.parse(sessionStorage.getItem("identity"));
                },
             getAll(){
                 request.post("/leaveMessage/getAllMessage",this.PageInfo).then((res)=>{
                    console.log(res.data);
                    this.PageInfo.pageNum = res.data.pageInfo.pageNum;
                    this.PageInfo.pageSize = res.data.pageInfo.pageSize;
                    this.totalSize = res.data.pageInfo.totalSize;
                    this.messages=res.data.pageInfo.pageData
                    for (let i = 0; i < this.messages.length; i++) {
                        request.get("/files/initAvatar/" + this.messages[i].avatar).then((res) => {
                       if (res.code === 200) {
                        this.messages[i].image =  res.data.data
                       } else { 
                         this.messages[i].image =  "";
                    }});
                    }
                })
        },
            deleteMessage(row){
                request.delete("/leaveMessage/"+row.id).then((res) => {
                        if (res.code === 200) {
                            this.$message.success("删除成功");
                            this.getAll()
                            this.form.content = "";
                        } else {
                            this.$message.error("出错了");
                        }
                    });
            },
            send(){
                this.form.from = JSON.parse(sessionStorage.getItem("user")).uid;
                request.post("/leaveMessage", this.form).then((res) => {
                        if (res.code === 200) {
                            this.$message.success("感谢评论");
                            this.getAll()
                            this.form.content = "";
                        } else {
                            this.$message.error("出错了");
                        }
                    });
        },
          init(data) {
             request.get("/files/initAvatar/" + data).then((res) => {
                    if (res.code === 200) {
                        return res.data.data;
                    } else { 
                        return "error";
                        
                       
                    }
                });
            
        },
    }
    }
</script>

<style>
        .input-content {
        position: absolute;
        bottom: 0;
        left: 50%;
        transform: translateX(-50%);
        margin-bottom: 100px;
        }
</style>