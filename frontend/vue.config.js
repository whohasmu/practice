module.exports = {
  outputDir: "../src/main/resources/static",
  indexPath: "../src/main/resources/templates/vue/index.html",
  devServer: {
    proxy: "http://localhost:9090"
  },
  chainWebpack: config => {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");
  }
};
