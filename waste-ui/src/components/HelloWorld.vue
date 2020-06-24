<template>
  <div class="hello">
    <el-card>
      <h1>柱状图bar</h1>
      <div id="bar" style="margin:100px;">
        <h2>普通示例</h2>
        <ve-histogram :data="chartData3"></ve-histogram>
        <h2>设置显示的指标维度</h2>
        <small>在setting里指定了只显示“访问用户”和“下单用户”</small>
        <div>
          <pre>
          this.bar2_chartSettings = {
          metrics: ["访问用户", "下单用户"],
          dimension: ["日期"]
          };
        </pre>
        </div>
        <ve-histogram :data="chartData3" :settings="{metrics: ['访问用户', '下单用户'], dimension: ['日期']}"></ve-histogram>
        <h2>设置双y轴</h2>
        <ve-histogram :data="chartData3" :settings="bar3_chartSettings"></ve-histogram>
        <h2>默认显示柱状图数据</h2>
        <ve-histogram :data="chartData3" :extend="extend"></ve-histogram>
        <h2>修改别名</h2>
        <ve-histogram :data="chartData3" :settings="bar4_chartSettings"></ve-histogram>
        <h2>设置横轴为连续的数值轴</h2>
        <small>x轴的数据必须是连续数据</small>
        <ve-histogram :data="chartData4" :settings="{xAxisType: 'value'}"></ve-histogram>
      </div>
      <h1>饼图pie</h1>
      <div id="pie" style="margin:100px">
        <h2>普通示例</h2>
        <ve-pie :data="chartData3"></ve-pie>
        <h2>设置指标维度</h2>
        <small>
          <b>dimension</b>默认columns第一项为维度
          <br />
          <b>metrics</b>默认columns第二项为指标
        </small>
        <ve-pie :data="chartData3" :settings="{dimension: '日期',metrics: '访问用户'}"></ve-pie>
        <h2>玫瑰图</h2>
        <ve-pie :data="chartData3" :settings="{roseType: 'radius'}"></ve-pie>
        <h2>限制显示条数饼图</h2>
        <small>
          <code>limitShowNum: 5</code>
          <br />
          显示为：{前5项，其他}
          <br />legend数量过多会导致饼图样式错误，限制legend最大值并且当超过此值时，隐藏legend可以解决这个问题
        </small>
        <ve-pie :data="chartData3" :settings="{limitShowNum: 5}"></ve-pie>
        <h2>多圆饼图</h2>
        <small>分组显示</small>
        <ve-pie
          :data="chartData3"
          :settings="{
            level: [
              ['1/1', '1/2', '1/3'],
              ['1/4', '1/5']
            ]}"
        ></ve-pie>
        <h2>设置数据类型</h2>
        <small>
          <b>dataType</b>可选值: KMB, normal, percent
        </small>
        <h3>percent</h3>
        <ve-pie :data="chartData3" :settings="{dataType: 'percent'}"></ve-pie>
        <h3>KMB</h3>
        <small>以kilo为单位的金钱</small>
        <ve-pie :data="chartData3" :settings="{dataType: 'KMB'}"></ve-pie>

        <h2>设置饼图半径边距</h2>
        <ve-pie :data="chartData3" :settings="{radius: 10, offsetY: 300}"></ve-pie>
      </div>
      <h1>瀑布图</h1>
      <div id="funnel" style="margin:100px; width:400px">
        <h2>普通示例</h2>
        <ve-funnel :data="chartData5"></ve-funnel>
        <h2>指定指标维度</h2>
        <small>:settings="{dimension: '状态1', metrics: '数值'}"</small>
        <ve-funnel :data="chartData5" :settings="{dimension: '状态1', metrics: '数值'}"></ve-funnel>
        <h2>自动按照数值排序并过滤0值</h2>
        <ve-funnel :data="chartData5" :settings="{useDefaultOrder: true, filterZero: true}"></ve-funnel>
        <h2>定制顺序漏斗图</h2>
        <ve-funnel :data="chartData5" :settings="{sequence: ['订单', '点击', '访问', '展示']}"></ve-funnel>
        <h2>指定数据类型漏斗图</h2>
        <ve-funnel :data="chartData5" :settings="{dataType: 'percent'}"></ve-funnel>
        <h2>修改legend别名漏斗图</h2>
        <ve-funnel :data="chartData5" :settings="{legendName: {'订单': '订单 total:1000'}}"></ve-funnel>
        <h2>金字塔</h2>
        <ve-funnel :data="chartData5" :settings="{ascending: true}"></ve-funnel>
      </div>
    </el-card>
    <el-card>
      <h1>{{ msg }}</h1>
      <h2>Essential Links</h2>
      <ul>
        <li>
          <a href="https://vuejs.org" target="_blank">Core Docs</a>
        </li>
        <li>
          <a href="https://forum.vuejs.org" target="_blank">Forum</a>
        </li>
        <li>
          <a href="https://chat.vuejs.org" target="_blank">Community Chat</a>
        </li>
        <li>
          <a href="https://twitter.com/vuejs" target="_blank">Twitter</a>
        </li>
        <br />
        <li>
          <a href="http://vuejs-templates.github.io/webpack/" target="_blank">Docs for This Template</a>
        </li>
      </ul>
      <h2>Ecosystem</h2>
      <ul>
        <li>
          <a href="http://router.vuejs.org/" target="_blank">vue-router</a>
        </li>
        <li>
          <a href="http://vuex.vuejs.org/" target="_blank">vuex</a>
        </li>
        <li>
          <a href="http://vue-loader.vuejs.org/" target="_blank">vue-loader</a>
        </li>
        <li>
          <a href="https://github.com/vuejs/awesome-vue" target="_blank">awesome-vue</a>
        </li>
      </ul>
    </el-card>
    <div>
      <el-card>
        <h2>图表</h2>
        <div class="hello">
          <chart ref="chart1" :options="orgOptions" :auto-resize="true"></chart>
        </div>
        <ve-line :data="chartData1"></ve-line>
        <ve-pie :data="chartData2" :settings="chartSettings1"></ve-pie>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  data() {
    this.chartSettings1 = {
      dataType: function(v) {
        return v + " ￥";
      }
    };
    this.bar3_chartSettings = {
      axisSite: { right: ["下单率"] },
      yAxisType: ["KMB", "percent"],
      yAxisName: ["数值", "比率"]
    };
    this.extend = {
      series: {
        label: { show: true, position: "top" }
      }
    };
    this.bar4_chartSettings = {
      labelMap: {
        PV: "访问用户",
        Order: "下单用户"
      },
      legendName: {
        访问用户: "访问用户 total: 10000"
      }
    };
    return {
      msg: "Welcome to Your Vue.js App",
      orgOptions: {},
      chartData2: {
        columns: ["日期", "销售量"],
        rows: [
          { 日期: "1月1日", 销售量: 123 },
          { 日期: "1月2日", 销售量: 1223 },
          { 日期: "1月3日", 销售量: 2123 },
          { 日期: "1月4日", 销售量: 4123 },
          { 日期: "1月5日", 销售量: 3123 },
          { 日期: "1月6日", 销售量: 7123 }
        ]
      },
      chartData1: {
        columns: ["date", "PV", "Order"],
        rows: [
          { date: "2018-05-22", PV: 32371, Order: 19810 },
          { date: "2018-05-23", PV: 12328, Order: 4398 },
          { date: "2018-05-24", PV: 92381, Order: 52910 }
        ]
      },
      chartData3: {
        columns: ["日期", "访问用户", "下单用户", "下单率"],
        rows: [
          { 日期: "1/1", 访问用户: 1393, 下单用户: 1093, 下单率: 0.32 },
          { 日期: "1/2", 访问用户: 3530, 下单用户: 3230, 下单率: 0.26 },
          { 日期: "1/3", 访问用户: 2923, 下单用户: 2623, 下单率: 0.76 },
          { 日期: "1/4", 访问用户: 1723, 下单用户: 1423, 下单率: 0.49 },
          { 日期: "1/5", 访问用户: 3792, 下单用户: 3492, 下单率: 0.323 },
          { 日期: "1/6", 访问用户: 4593, 下单用户: 4293, 下单率: 0.78 }
        ]
      },
      chartData4: {
        columns: ["日期", "访问用户", "下单用户", "下单率"],
        rows: [
          { 日期: 1, 访问用户: 1393, 下单用户: 1093, 下单率: 0.32 },
          { 日期: 2, 访问用户: 3530, 下单用户: 3230, 下单率: 0.26 },
          { 日期: 5, 访问用户: 2923, 下单用户: 2623, 下单率: 0.76 },
          { 日期: 10, 访问用户: 1723, 下单用户: 1423, 下单率: 0.49 },
          { 日期: 20, 访问用户: 3792, 下单用户: 3492, 下单率: 0.323 },
          { 日期: 22, 访问用户: 4593, 下单用户: 4293, 下单率: 0.78 }
        ]
      },
      chartData5: {
        columns: ["状态", "数值"],
        rows: [
          { 状态: "展示", 数值: 900 },
          { 状态: "访问", 数值: 600 },
          { 状态: "点击", 数值: 300 },
          { 状态: "订单", 数值: 100 }
        ]
      }
    };
  },
  mounted() {
    this.orgOptions = {
      xAxis: {
        type: "category",
        data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
      },
      yAxis: {
        type: "value"
      },
      series: [
        {
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: "line",
          smooth: true
        }
      ]
    };
  },
  created(){
    console.log("cd2",this.chartData2)
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
