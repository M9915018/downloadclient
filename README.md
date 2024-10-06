專案改編至git@github.com:niumoo/down-bit.git

## 多线程断点续传下载器 - DownBit

### 更新记录
2020-12-27：:art: 支持迅雷链接解析下载  
2020-09-24：:art: 优化日志输出方式 :bug: 修复部分服务器文件不能正常分段下载问题  
2020-08-03：:bug: 修复部分文件下载完毕后，续传时日志线程卡死问题  
2020-07-26：:tada: 多线程断点续传下载初始版本

### 使用方式
```shell
git clone git@github.com:niumoo/down-bit.git
cd down-bit
mvn package
java -jar target/down-bit-jar-with-dependencies.jar 下载链接
```

### 相关知识
[撸了个多线程断点续传下载器，我从中学习到了这些知识](https://mp.weixin.qq.com/s/bI5xYq3jUtp-sviKlzHtNg)
### 联系我



