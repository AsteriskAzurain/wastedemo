<template>
  <div>
    <el-container>
      <!-- 公告板 -->
      <el-aside width="400px">
        <h1 @click="noticemsg">公告板</h1>
        <div id="notice">
          <el-card class="notice-card" shadow="hover" v-for="item in notices" :key="item.key">
            <div slot="header" class="clearfix">
              <span>{{item.title}}</span>
              <span style="float: right; padding: 3px 0" type="text">{{item.date}}</span>
              <!-- {{currentDate | comverTime('YYYY-MM-DD')}} -->
            </div>
            <div class="cardbody text item" v-html="item.content"></div>
          </el-card>
        </div>
      </el-aside>
      <!-- 垃圾分类知识 -->
      <el-main>
        <h1>垃圾分类知识</h1>
        <div id="know" >
          <el-card class="know-card" shadow="hover" v-for="item in knows" :key="item.key">
            <div slot="header" class="clearfix">
              <span>{{item.title}}</span>
            </div>
            <img :src="item.imgurl" />
            <div v-html="item.content" class="cardbody"></div>
          </el-card>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import MyHeader from "@/components/header";

export default {
  name: "home",
  data() {
    return {
      currentDate: new Date(),
      img:
        "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
      knows: [
        {
          key: 1,
          title: "生活垃圾一般可分为四大类",
          content:
            "<span>1）可回收垃圾；<br>2）湿垃圾；<br>3）有害垃圾；<br>4）干垃圾。</span>",
          imgurl:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592759290597&di=3f5d2082d31dd485a58fb5b7e2206600&imgtype=0&src=http%3A%2F%2Fie.bjd.com.cn%2Fimages%2F201907%2F26%2F5d3a507de4b057bee19db914.gif"
        },
        {
          key: 2,
          title: "有害垃圾的具体范围",
          content:
            "<p>一、电池类<br>纽扣电池、充电电池（如手机电池）、铅酸电池、蓄电池等。不含普通干电池（如 1号、5号、7号电池，因具生产已达到国家低汞或无汞技术要求，现作为其他垃圾投放）。</p><p>二、含汞类<br>废荧光灯管、废节能灯、废水银温度计、废水银血压计、 荧光棒、水银体温计、水银血压计等。</p><p>三、废药类<br>过期药品、药片、过期胶囊药品、药品包装等。</p><p>四、油漆、废农药类<br>废油漆桶、染发剂壳、洗甲水、过期指甲油、消毒液、老鼠药、杀虫剂等。</p>",
          imgurl:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592759290597&di=b9a1f622477e45c049714b610dea20eb&imgtype=0&src=http%3A%2F%2Fupload.cheaa.com%2F2020%2F0512%2F1589271244282.jpg"
        },
        {
          key: 3,
          title: "垃圾分类原则",
          content:
            "<p>1、分而用之<br>分类的目的就是为了将废弃物分流处理，利用现有生产制造能力，回收利用回收品，包括物质利用和能量利用，填埋处置暂时无法利用的无用垃圾。</p><p>2、因地制宜<br>各地、各区、各社（区）、各小区地理、经济发展水平、企业回收利用废弃物的能力、居民来源、生活习惯、经济与心理承担能力等各不相同。</p><p>3、自觉自治<br>社区和居民，包括企事业单位，逐步养成“减量、循环、自觉、自治”的行为规范，创新垃圾分类处理模式，成为垃圾减量、分类、回收和利用的主力军。</p><p>4、减排补贴 超排惩罚<br>制定单位和居民垃圾排放量标准，低于这一排放量标准的给予补贴；超过这一排放量标准的则予以惩罚。减排越多补贴越多，超排越多惩罚越重，以此提高单位和居民实行源头减量和排放控制的积极性。</p><p>5、捆绑服务 注重绩效<br>在居民还没有自愿和自觉行动而居（村）委和政府的资源又不足时，推动分类排放需要物业管理公司和其他企业介入。但是，仅仅承接分类排放难以获利，企业不可能介入，而推行捆绑服务就能要解决这个问题。将推动分类排放服务与垃圾收运、干湿垃圾处理业务捆绑，可促进垃圾分类资本化，保障企业合理盈利。</p>",
          imgurl:
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3276229441,246699191&fm=26&gp=0.jpg"
        }
      ],
      notices: [
        {
          key: 1,
          title: "关于生活垃圾分类的公告",
          date: "2019-9-2",
          content:
            "<strong>尊敬的各位业主朋友：</strong><p>您好！《xx市生活垃圾分类管理办法》自 2019年9月1日起实行 ，违反规定随意投放生活垃圾的，单位 罚款5000以上20000以下 ，个人罚款 100以上200以下 ，并将不良信息纳入个人征信系统。</p><p>我们的城市每天都会产生大量的垃圾，日益增长的垃圾给我们有限的生存环境带来了沉重的压力。倡导低碳生活，做好垃圾分类势在必行。2019年9月1日起，西安全面开始实行垃圾分类投放，为更好的引导广大业主做好垃圾分类，城市理想客户服务中心将于2019年9月2日下午17:30将对公区内、地下车库垃圾桶集中回收清理，方便现场进行垃圾分类投放指导，给您生活带来不便，敬请谅解！为了更加美好的环境，为了子孙未来，特号召各位业主/住户做好以下几点：</p><ol><li>请随时留意身边各种宣传资料及宣传活动，（垃圾分类资料可到物业服务中心免费索取），了解垃圾分类政策，掌握分类方法。现小区垃圾分类分为四种：厨余垃圾、其他垃圾、有害垃圾、可回收垃圾；</li> <li>小区集中垃圾投放点，具体位置为：西门垃圾站北侧垃圾分类指定区域；</li> <li>垃圾投放时间：早上：7:00至晚上20:00。</li></ol><p>望各位业主/住户相互告知，坚持从源头减量，践行低碳节约、循环利用的生活方式。严格按照生活垃圾分类的标准要求，耐心严谨地将生活垃圾分类投放。感谢您对环保做出的每一份努力！</p><span style='float:right;text-align:right;margin-bottom:20px;'>XXX物业<br>2019年9月2日 </span>"
        },
        {
          key: 2,
          title: "关于开展垃圾分类活动的公告",
          date: "2019-6-30",
          content:
            "<strong>亲爱的各位业主朋友：</strong><p>为了积极响应国家<a href='https://www.bsccu.com/posts-by-cat' style='color:red;'>垃圾分类处理</a>的号召，提高我们城市生活垃圾减量化、资源化、无害化的水平，按照街道的统一安排和部署，我们小区拟在小区全面开展垃圾分类活动，垃圾分类的具体通知如下：</p><p>街道初步计划将<strong>生活垃圾按照可回收物、有害垃圾、湿垃圾、干垃圾进行分类</strong>，而且生活垃圾的具体分类标准可根据经济社会发展水平、生活垃圾特性和处置利用需要和街道的进一步部署安排予以调整。所以我们小区计划于该通知发布三天后在小区物业举行垃圾分类活动启动仪式，届时将会现场发放家用分类垃圾桶，请各位业主携带房产证领取，当天未及时领取的业户请于物业的上班时间到物业管理处凭证领取。</p><p>垃圾分类的具体要求如下：<strong>适宜回收和进行资源利用的可回收垃圾用蓝色容器收集垃圾，有害垃圾，即表示含有害物质并且需要进行特殊安全处理的垃圾用红色容器收集垃圾，其它垃圾即表示除可回收物、有害垃圾、湿垃圾分类以外的垃圾也就是我们常说的干垃圾，用灰色容器收集垃圾。</strong></p><p>垃圾分类不仅仅是政府的事情，更是关系到每个人生活质量的事情，是一件民生大事。让我们，从我做起，从一点一滴做起，为我们创建一个干净整洁的生活环境，为我们的后代留下绿水青山。</p><span style='float:right;text-align:right;margin-bottom:20px;'>XXX物业<br>2019年6月30日</span>"
        }
      ]
    };
  },
  computed: {},
  methods: {
    noticemsg() {
      const h = this.$createElement;

      this.$notify({
        title: "公告板提示",
        message: h(
          "i",
          { style: "color: teal" },
          "id:notice<br>notice board<br>此处放置公告内容<br>"
        )
      });
    }
  },
  components: {
    MyHeader
  }
};
</script>

<style>
.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 1.5em;
}

.el-aside h1,
h2,
h3,
h4,
h5,
h6 {
  line-height: 60px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 1.5em;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

/* mystyle */

.notice-card {
  margin: 20px;
}

.know-card {
  margin: 20px;
  height: fit-content;
  min-height: 250px;
  width: 100%;
}

#know img {
  width: 250px;
  float: right;
  margin: 20px;
}

.cardbody {
  text-align: left;
}
</style>