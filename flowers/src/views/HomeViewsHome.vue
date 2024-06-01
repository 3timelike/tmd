<template>
    <el-card style="margin: 15px; min-height: calc(100vh - 80px)">
        <!--    头部数据-->
        
        <!-- 下部-->
        <div style="display: flex;width: 100%;margin-top: 40px;align-items: center;justify-content: center;">
            <!--   左侧 宿舍通告-->
            <!-- <div style="margin-right: 5%">
                <span style="font-size: 22px;display: block;margin-bottom: 30px;margin-left: 10px;">宿舍通告</span>
                <el-timeline>
                    <el-timeline-item v-for="(activity, index) in activities.slice(0, 8)" :key="index"
                                      :timestamp="activity.releaseTime">
                        <span style="font-size: 15px">{{ activity.title }}</span><br/>
                       <span style="font-size: 10px">{{ activity.content }}</span>
                    </el-timeline-item>
                </el-timeline>
            </div> -->
            <!--   中部-->
            <div style="height: 588px">
        <!-- <span style="
            font-size: 22px;
            display: block;
            margin-bottom: 30px;
            margin-left: 10px;
               " >宿舍学生人数分布</span>
                <home_echarts/> -->
            </div>
            <!--  右侧-->
            <div style="margin-left: 5%">
                <!--   天气组件-->
                <weather/>
                <!--    日历组件-->
                <el-card style="width: 380px; max-height: 440px; margin-top: 17px">
                    <Calender/>
                </el-card>
            </div>
        </div>
    </el-card>
</template>

<script src="@/assets/js/Home.js"></script>
<style scoped>@import '../assets/css/Home.css';</style>