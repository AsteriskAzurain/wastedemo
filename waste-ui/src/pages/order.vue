<template>
  <div>
    <el-tabs tab-position="left" type="border-card" @tab-click="handleClick">
      <!-- 标签1 -->
      <el-tab-pane label="附近回收点">
        <h1>附近回收点</h1>
        <div>
          <el-button
            type="primary"
            size="mini"
            plain
            round
            @click="loadsitelist"
            style="margin-right:20px"
          >查找所有</el-button>
          <el-select size="mini" v-model="area" placeholder="请选择站点区域" @change="areaaction">
            <el-option
              v-for="item in arealist"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </div>
        <el-table
          :data="sitelist.filter(data => !search || data.sitename.toLowerCase().includes(search.toLowerCase()) || (data.area.includes(search)))"
          style="width: 100%"
        >
          <el-table-column label="站点ID" prop="id" width="100"></el-table-column>
          <el-table-column label="所在区域" prop="area" width="150" sortable></el-table-column>
          <el-table-column label="站点名" prop="sitename" width="200"></el-table-column>
          <el-table-column label="详细地址" prop="address" min-width="20%"></el-table-column>
          <el-table-column align="right" width="300">
            <template slot="header" slot-scope="scope">
              <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <!-- 标签2 -->
      <el-tab-pane label="下单">
        <h1>下单</h1>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>结算</span>
          </div>
          <div class="text item">
            <h4>回收件数：{{orderForm.totalnumber}}件</h4>
            <h4>回收总价：{{orderForm.totalprice}}元</h4>
          </div>
        </el-card>
        <el-form :model="orderForm" ref="orderFrom" label-width="100px" class="demo-dynamic">
          <el-form-item prop="site" label="回收站点" :rules="[]">
            <el-select v-model="orderForm.siteid" placeholder="请选择">
              <el-option
                v-for="item in sitelist"
                :key="item.id"
                :label="item.sitename"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item
            v-for="(domain, index) in orderForm.detail"
            :label="'回收垃圾' + index"
            :key="domain.key"
            :prop="'detail.' + index + '.value'"
          >
            <el-select
              v-model="domain.rubbishid"
              placeholder="请选择"
              @change="((val)=>{selectaction(val, domain)})"
            >
              <el-option
                v-for="item in rubbishlist"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
            <el-input-number
              v-model="domain.count"
              controls-position="right"
              @change="((val)=>{handleChange(val, domain)})"
              :min="1"
              :max="10"
            ></el-input-number>
            <el-button @click.prevent="removeDomain(domain)">删除</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('orderFrom')">提交</el-button>
            <el-button @click="addDomain">添加回收垃圾</el-button>
            <el-button @click="resetForm('orderFrom')">重置</el-button>
          </el-form-item>

          <el-form-item>
            <el-input v-model="orderForm.userid" type="hidden">userid</el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <!-- 标签3 -->
      <el-tab-pane label="订单查询">
        <h1>订单查询</h1>
        <el-table :data="orderlist" style="width:100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-table :data="props.row.detail" stripe max-height="300" style="width:50%">
                <el-table-column prop="id" label="明细编号" width="100"></el-table-column>
                <el-table-column prop="rubbishid" label="垃圾类别" width="160"></el-table-column>
                <el-table-column prop="price" label="单件价格" width="100"></el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column label="订单ID" prop="order.id" width="100"></el-table-column>
          <el-table-column label="站点名" prop="order.siteid" width="200"></el-table-column>
          <el-table-column label="积分数" prop="order.pointnumber" width="100"></el-table-column>
          <el-table-column label="回收数量" prop="order.totalnumber" width="100"></el-table-column>
          <el-table-column label="回收价格" prop="order.totalprice" width="100"></el-table-column>
          <el-table-column label="创建时间" prop="order.createtime" sortable></el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    const cuser = this.$cookies.get("currentuser");
    if (cuser == null) {
      this.$message({
        message: "您尚未登录，请登陆后重试",
        type: "error"
      });
    }
    return {
      addcount: 0,
      currentuser: cuser,
      orderForm: {
        userid: cuser.id,
        siteid: 1,
        pointnumber: 100,
        totalnumber: 0,
        totalprice: 0,
        createtime: Date.now(),
        delFlag: true,
        detail: []
      },
      fullsitelist: [],
      sitelist: [],
      area: "",
      arealist: [],
      rubbishlist: [],
      orderlist: [],
      search: ""
    };
  },
  methods: {
    async loadsitelist() {
      this.area = "";
      this.search = "";
      const { data: res } = await this.$http.post(
        "http://localhost:8001/recycle/site/findall"
      );
      if (res.code == 200) {
        // let siteobj = res.data;
        // let sitearr = [];
        // for (let i in siteobj) {
        //   sitearr.push(siteobj[i]);
        // }
        let sitearr = Array.from(res.data);
        this.sitelist = sitearr;
        this.fullsitelist = sitearr;
        // if (this.sitelist instanceof Array) console.log("sitelist is Array");
      }
    },
    async loadarealist() {
      const { data: res } = await this.$http.post(
        "http://localhost:8001/recycle/site/findallarea"
      );
      if (res.code == 200) {
        let areaobj = res.data;
        let areaarr = [];
        let index = 0;
        for (let i in areaobj) {
          if (areaarr.findIndex(str => str.label == areaobj[i]) == -1) {
            let obj = {
              value: index,
              label: areaobj[i]
            };
            areaarr.push(obj);
            index++;
          }
        }
        this.arealist = areaarr;
      }
    },
    areaaction(val) {
      let areaname = this.arealist[val].label;
      console.log("selected:", val, areaname);
      this.sitelist = this.fullsitelist.filter(
        data => !areaname || data.area.includes(areaname)
      );
    },
    async loadrubbishtype() {
      const { data: res } = await this.$http.post(
        "http://localhost:8001/rubbish/info/selectall"
      );
      if (res.code == 200) {
        this.rubbishlist = res.data;
      }
    },
    async loadownorder() {
      const uid = this.currentuser.id;
      if (uid > 0) {
        const {
          data: res
        } = await this.$http.post(
          "http://localhost:8001/recycle/order/selectownorder",
          { id: uid }
        );
        if (res.code == 200) {
          this.orderlist = res.data;
          this.orderlist.forEach(element => {
            let findid = element.order.siteid;
            let findname = this.fullsitelist.find(obj => obj.id == findid)
              .sitename;
            // console.log(findid,findname)
            element.order.siteid = findname;
            let date = new Date(element.order.createtime);
            let year = date.getFullYear(); //年
            let month = date.getMonth()+1; //月
            let day = date.getDate(); //日
            let hours = date.getHours(); //时
            let min = date.getMinutes(); //分
            let second = date.getSeconds(); //秒
            let datestr =
              year + "-" + month + "-" + day + " " + hours + ":" + min;
            element.order.createtime = datestr;

            element.detail.forEach(element => {
              let rid = element.rubbishid;
              let rinfo = this.rubbishlist.find(obj => obj.id == rid);
              let rname = rinfo.name;
              let rprice = rinfo.price + "元";
              // console.log("rubbish",rid,rname)
              element.rubbishid = rname;
              this.$set(element, "price", rprice);
            });
          });
          // console.log("rubbish list", this.rubbishlist);
        }
      }
    },
    handleClick(tab, event) {
      this.loadownorder();
      console.log(tab, event);
    },
    submitForm(formName) {
      // console.log("domain update", this.orderForm.detail);
      this.orderForm.createtime = new Date();
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log(this.orderForm);
          this.$http
            .post("http://localhost:8001/recycle/order/add", this.orderForm)
            .then(res => {
              if (res.data.code == 200) {
                this.$message({
                  message: res.data.msg,
                  type: "success"
                });
              } else {
                this.$message({
                  message: res.data.msg,
                  type: "error"
                });
              }
            });
        } else {
          this.$message({
            message: "下单失败",
            type: "error"
          });
          return false;
        }
      });
      this.$refs[formName].resetFields();
    },
    resetForm(formName) {
      console.log("reset!");
      this.$refs[formName].resetFields();
    },
    removeDomain(item) {
      var index = this.orderForm.detail.indexOf(item);
      if (index !== -1) {
        this.orderForm.detail.splice(index, 1);
      }
    },
    addDomain() {
      this.orderForm.detail.push({
        userid: this.currentuser.id,
        rubbishid: 1,
        count: 0,
        subprice: 2,
        subtotal: 2,
        delFlag: true,
        key: Date.now()
      });
    },
    selectaction(value, domain) {
      // console.log(value, domain);
      let rid = domain.rubbishid;
      let rinfo = this.rubbishlist.find(obj => obj.id == rid);
      let price = rinfo.price;
      domain.rubbishid = value;
      domain.subprice = price;
      domain.subtotal = price * domain.count;
    },
    handleChange(value, domain) {
      // console.log(value, domain);
      domain.count = value;
      domain.subtotal = domain.subprice * value;
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    }
  },
  created() {
    this.loadsitelist();
    this.loadarealist();
    this.search = "奉贤区";

    this.loadrubbishtype();
    this.loadownorder();
  },
  computed: {
    totalcount() {
      let tc = 0;
      this.orderForm.detail.forEach(element => {
        let c = element.count;
        tc += c;
      });
      return tc;
    },
    sumsubprice() {
      let tp = 0;
      this.orderForm.detail.forEach(element => {
        let p = element.subtotal;
        tp += p;
      });
      return tp;
    }
  },
  watch: {
    totalcount(val) {
      // console.log("t c:", val);
      this.orderForm.totalnumber = val;
    },
    sumsubprice(val) {
      this.orderForm.totalprice = val;
    }
  }
};
</script>

<style>
.el-header,
.el-footer {
  color: #333;
  text-align: center;
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

.el-tab-pane {
  text-align: left;
}

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 300px;
  position: absolute;
  right: 5%;
}
</style>