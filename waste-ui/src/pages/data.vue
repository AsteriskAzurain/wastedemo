<template>
  <div>
    <div id="charts" style="margin:50px;">
      <el-card>
        <div slot="header" style="text-align:center">
          <h1>月度社区垃圾投掷数量统计图</h1>
        </div>
        <el-row :gutter="20">
          <el-col :span="16">
            <div class="grid-content" style="margin-left:auto;">
              <ve-pie
                :data="chartdata2"
                :settings="{labelMap: {
              id: '垃圾编号',
              count: '本月投掷数量'
            },legendName: {
              1: '废纸张',2:'废塑料',3:'废玻璃',4:'废金属',5:'废织品',6:'其他可回收物'
            }}"
              ></ve-pie>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <ve-funnel
                :data="chartdata2"
                width="300px"
                :settings="{labelMap: {
              id: '垃圾编号',
              count: '本月投掷数量'
            },legendName: {
              1: '废纸张',2:'废塑料',3:'废玻璃',4:'废金属',5:'废织品',6:'其他可回收物'
            }}"
              ></ve-funnel>
            </div>
          </el-col>
        </el-row>
      </el-card>

      <el-card style="text-align:center">
        <div slot="header" class="clearfix">
          <h1>月度可回收订单统计-业主订单榜</h1>
        </div>
        <div style="width:300px; margin:0 auto;">
          <ve-histogram
            :data="chartdata2"
            :settings="{labelMap: {
              id: '用户编号',
              count: '本月下单数'
            }}"
          ></ve-histogram>
        </div>
      </el-card>

      <el-card style="text-align:center">
        <div slot="header" class="clearfix">
          <h1>月度可回收订单统计-业主数量榜</h1>
        </div>

        <div style="width:300px; margin:0 auto;">
          <ve-histogram
            :data="chartdata4"
            :settings="{labelMap: {
              id: '用户编号',
              count: '投掷垃圾数'
            }}"
          ></ve-histogram>
        </div>
      </el-card>

      <el-card style="text-align:center">
        <div slot="header" class="clearfix">
          <h1>月度可回收订单统计-垃圾投掷榜</h1>
        </div>

        <div style="float:right">
          1: '废纸张',
          <br />2:'废塑料',
          <br />3:'废玻璃',
          <br />4:'废金属',
          <br />5:'废织品',
          <br />6:'其他可回收物'
        </div>
        <div style="width:300px; margin:0 auto;">
          <ve-histogram
            :data="chartdata3"
            :settings="{labelMap: {
              id: '垃圾编号',
              count: '被投掷数'
            },legendName: {
              1: '废纸张',2:'废塑料',3:'废玻璃',4:'废金属',5:'废织品',6:'其他可回收物'
            }}"
          ></ve-histogram>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import VeLine from "v-charts/lib/line.common";

export default {
  name: "Echarts",
  data() {
    return {
      activeNames: ["1"],
      chartdata1: {
        columns: ["id", "count"],
        rows: []
      },
      chartdata2: {
        columns: ["id", "count"],
        rows: []
      },
      chartdata3: {
        columns: ["id", "count"],
        rows: []
      },
      chartdata4: {
        columns: ["id", "count"],
        rows: []
      }
    };
  },
  methods: {
    handleChange(val) {
      console.log(val);
    },
    async loadchartdata() {
      const { data: res1 } = await this.$http.post(
        "http://localhost:8001/statistic/loaddata1"
      );
      if (res1.code == 200) {
        let temp1 = res1.data;
        this.chartdata1.rows = temp1.rows;
      }

      const { data: res2 } = await this.$http.post(
        "http://localhost:8001/statistic/loaddata2"
      );
      if (res2.code == 200) {
        let temp2 = res2.data;
        this.chartdata2.rows = temp2.rows;
      }

      const { data: res3 } = await this.$http.post(
        "http://localhost:8001/statistic/loaddata3"
      );
      if (res3.code == 200) {
        this.chartdata3.rows = res3.data.rows;
      }

      const { data: res4 } = await this.$http.post(
        "http://localhost:8001/statistic/loaddata4"
      );
      if (res4.code == 200) {
        this.chartdata4.rows = res4.data.rows;
        console.log("data4", this.chartdata4);
      }
    }
  },
  mounted() {},
  created() {
    this.loadchartdata();
  }
};
</script>

<style>
.el-collapse-item__header {
  text-align: left;
  line-height: 1.5rem;
  font-size: 17px;
  height: 80px;
  font-weight: bolder;
  color: midnightblue;
}

h1 {
  color: midnightblue;
}

.el-card{
  margin:40px;
}
</style>