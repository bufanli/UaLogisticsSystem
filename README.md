# UaLogisticsSystem

##1. jave-1.0.2.jar 这个包在目前的aliyun的maven库里面已经没有了，所以把这个包上去，放到自己的maven包的目录下。 
     比如，目前的本机环境是 C:\Users\Dell\.m2\repository\it\sauronsoftware\jave\1.0.2
##2. DB连接，因为mysql的升级成8.0.13,需要对mysql的连接url进行改修。useSSL=false让SSL认证无效。serverTimezone需要设置
   改修的配置文件时，spring.profiles.active=dev已经设置成了dev，所以注意修改application-dev.properties
   jdbc:mysql://localhost:3306/parkinglot?characterEncoding=utf8&useSSL=false&serverTimezone=GMT
##3. 在本地的mysql数据库建立parkinglot的database。
##4. 改程序需要依赖redis，所以要下载redis，可以在windows窗口启动。
##5. intelliJ的module设定
    如果打开工程之后，IntelliJ没有自动添加module,project structure -> modules -> add -> Import Module
    
##todo 
1. db上没有建立任何表，需要调试之后确认。
