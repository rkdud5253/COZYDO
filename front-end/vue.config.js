const fs = require('fs');
module.exports = {
  transpileDependencies: ["vuetify"],
  devServer: {
    disableHostCheck: true,
    port: 3000,
    https: true,
    https: {
      key: fs.readFileSync('./Deploy/domain.com.key'),
      cert: fs.readFileSync('./Deploy/domain.com.crt'),
      ca: fs.readFileSync('./Deploy/server.crt'),
    }
  }
};