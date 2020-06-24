<template>
  <div>
    <div id="changebtn">
      <el-button type="primary" size="medium" icon="el-icon-refresh" @click="change3">换一换</el-button>
    </div>
    <div id="knowdiv">
      <el-card class="know-card" shadow="hover" v-for="id in random3" :key="id">
        <div slot="header" class="clearfix">
          <h2>{{knows[id].title}}</h2>
        </div>
        <img :src="knows[id].imgurl" />
        <div v-html="knows[id].content" class="cardbody"></div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
        },
        {
          key: 4,
          title: "餐厨垃圾",
          content:
            "<p>根据城市实际分为家庭中产生的易腐食物垃圾（剩菜剩饭、动物类废弃物、废弃食物等）和餐饮服务单位（餐馆、饭店、宾馆等餐饮企业以及单位食堂）在食品加工、饮食服务、供餐等活动中产生的垃圾（食物残渣、残液、废弃油脂等）。</p>",
          imgurl:
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3742553723,1408154094&fm=26&gp=0.jpg"
        },
        {
          key: 5,
          title: "可回收物",
          content:
            "<p>属于再生资源，指生活垃圾中未经污染、适宜回收循环利用的废物，主要包括废弃电器电子产品、废纸张、废塑料、废玻璃、废金属等五类，是现阶段生活垃圾分类的主要工作和影响垃圾减量的重要因素。</p>",
          imgurl:
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2142142353,1341383433&fm=26&gp=0.jpg"
        },
        {
          key: 6,
          title: "其他垃圾",
          content:
            "<p>除可回收物、有害垃圾、餐厨垃圾外的其他生活垃圾，即现环卫体系主要收集和处理的垃圾。主要包括：纸巾、菜帮菜叶、保鲜膜（袋）、食品袋、包装袋、瓜皮果壳、烟头等。</p>",
          imgurl:
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4078434864,1167823001&fm=26&gp=0.jpg"
        },
        {
          key: 7,
          title: "有害垃圾",
          content:
            "<p>生活垃圾中对人体健康或自然环境造成直接或潜在危害的物质，必须单独收集、运输、存贮，由环保部门认可的专业机构进行特殊安全处理。</p>",
          imgurl:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592759514503&di=c0add412c9d1ef3deff0a38a1b0ec6f3&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180703%2Fcae943e78fa049bc87c39a684e8a7617.jpeg"
        },
        {
          key: 8,
          title: "垃圾分类处理好处",
          content:
            "<p><ol><li>减少占地。生活垃圾中有些物质不易降解，使土地受到严重侵蚀。垃圾分类，去掉能回收的、不易降解的物质，能减少垃圾数量达60%以上。</li><li>减少环境污染。废弃的电池中含有金属汞、镉等有毒的物质，会对人类产生严重的危害;土壤中的废塑料会导致农作物减产;抛弃的废塑料被动物误食，会导致动物死亡。</li><li>变废为宝。中国每年使用塑料快餐盒达40亿个，方便面碗5~7亿个，一次性筷子数十亿支，这些占生活垃圾的8%~15%。1吨废塑料可回炼600公斤柴油。回收1500吨废纸，可免于砍伐用于生产1200吨纸的林木。1吨易拉罐熔化后能结成1吨很好的铝块，可少采20吨铝矿。</li></ol></p>",
          imgurl:
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2258591671,1445255655&fm=26&gp=0.jpg"
        },
        {
          key: 9,
          title: "误区",
          content:
            "<p><strong>误区一：大棒骨是餐厨垃圾</strong><br>事实上，大棒骨因为“难腐蚀”被列入“其它垃圾”。类似的还有玉米核、坚果壳、果核、鸡骨等则是餐厨垃圾。</p><p><strong>误区二：厕纸卫生纸，不可回收</strong><br>厕纸、卫生纸遇水即溶，不算可回收的“纸张”，类似的还有陶器、烟盒等，也不可回收。</p><p><strong>误区三：花生壳算其他垃圾</strong><br>“果壳瓜皮”的标识就是花生壳，的确属于餐厨垃圾。家里用剩的废弃食用油，目前也归类在“餐厨垃圾”。</p>",
          imgurl:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592759551874&di=179860dea7ba8ee491a0ebbcc6ff4955&imgtype=0&src=http%3A%2F%2Fwww.58heike.com%2Fuploads%2Fallimg%2F190404%2F0PF93L5_0.jpg"
        }
      ],
      random3: []
    };
  },
  methods: {
    change3() {
      // #knowdiv > div:nth-child(2)
      this.random3 = new Array(
        Math.round(Math.random() * 9),
        Math.round(Math.random() * 9),
        Math.round(Math.random() * 9)
      );
    }
  },
  computed: {},
  created() {
    this.change3();
  }
};
</script>

<style>
.know-card {
  margin: 20px;
  height: fit-content;
  min-height: 250px;
  max-width: 75%;
}

.know-card img {
  height: 200px;
  float: right;
  margin: 20px;
}

.cardbody {
  text-align: left;
}

.clearfix {
  margin: 0;
  line-height: 1em;
}

.clearfix h2 {
  text-align: center;
  line-height: 1em;
}

#changebtn {
  position: absolute;
  right: 5%;
}

#knowdiv :nth-child(2) {
  /* text-align:right; */
  margin: 0px 0px 0px auto;
}
</style>