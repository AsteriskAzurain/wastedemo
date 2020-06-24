<template>
  <div>
    <div>
      <el-card class="notice-card" shadow="hover" v-for="item in notices" :key="item.key">
        <div slot="header" class="clearfix">
          <h2>{{item.title}}</h2>
          <span style="float: right; padding: 3px 0;" type="text">{{item.date}}</span>
          <!-- {{currentDate | comverTime('YYYY-MM-DD')}} -->
        </div>
        <div class="cardbody text item" v-html="item.content"></div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "NoticeBoard",
  data() {
    return {
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
  }
};
</script>

<style>
.cardbody{
  padding: 50px;
}
</style>