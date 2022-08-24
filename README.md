# luobin_tianqituisong2
针对网上小程序推送天气消息进行二次优化与细节调整，更快速与方便的进行完成自己功能的开发
![微信截图_20220823191811](https://user-images.githubusercontent.com/49942853/186187246-57116212-e0ac-471d-a78e-406546b8a298.png)

教程开始：
1.注册微信测试账号，编辑模板 https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login
![image](https://user-images.githubusercontent.com/49942853/186187612-faac431e-c8e7-43e8-9cb5-7ec74f338cfb.png)
上图微信扫码登录

然后会看到如下：
![image](https://user-images.githubusercontent.com/49942853/186188268-e27e727b-9720-4365-89b9-aab78bb23d68.png)
上图中两个密钥为：appID ， appsecret 简称：A，B

向下翻，看到如下：按照步骤，先点1，再点2
![image](https://user-images.githubusercontent.com/49942853/186188977-ea12e1bb-4067-4c2c-84cf-20fbb1b1f6ae.png)
先用微信扫码，关注测试用的微信号

然后就会看到如下：
![image](https://user-images.githubusercontent.com/49942853/186189521-402ea10b-f73c-4493-8995-5bcf3c1bc516.png)
上图密钥为：微信号密钥，简称C

然后点击下图绿色按钮
![image](https://user-images.githubusercontent.com/49942853/186190709-2c05db84-a90d-4606-a799-a0a31a60e288.png)

然后看到如下：
![image](https://user-images.githubusercontent.com/49942853/186191271-e6eb05a3-46b7-47bd-bacf-989a98d47c56.png)

标题先自己随便写一个，什么都行，后面能再改
模板内容如下：
{{riqi.DATA}} {{beizhu.DATA}} 
{{riqi.DATA}} {{beizhu.DATA}} 
{{city.DATA}} 天气：{{tianqi.DATA}} {{fengli.DATA}} 
当前气温：{{wendu.DATA}} ({{low.DATA}} ~{{high.DATA}} )
{{ganmao.DATA}} 
{{meme.DATA}} 
今天是我们恋爱的第{{lianai.DATA}}天 
距离你的生日还有{{shengri.DATA}}天 
{{caihongpi.DATA}} 

点击提交后，会看到如下：
![image](https://user-images.githubusercontent.com/49942853/186192229-6bc555ae-fdfd-4267-8489-9da89b3900a6.png)
上述密钥为：模板密钥，简称 D

好！第一步完成
第二步：
彩虹屁平台注册
https://www.tianapi.com/

然后如下：
![image](https://user-images.githubusercontent.com/49942853/186193577-5d676b0e-47b8-4295-8aba-641b4849a082.png)

然后如下：点击“申请接口”
![image](https://user-images.githubusercontent.com/49942853/186193828-08f32465-8922-44aa-aaa8-9f2c90353f0f.png)

然后如下：鼠标放到1上，然后点击2的位置
![image](https://user-images.githubusercontent.com/49942853/186194237-d2c48b01-d366-40a0-ad72-bb3245abb15c.png)

然后就会看到如下：我的因为已经申请过了，你们自己申请就行了
![image](https://user-images.githubusercontent.com/49942853/186284825-5ca19809-d7ec-4e2b-90b8-fc0ab936d3c5.png)

如果申请结束后，返回首页的位置，你应该看到如下
![image](https://user-images.githubusercontent.com/49942853/186284980-f8486e43-e347-4ec7-b38f-9c56b5dcf1f0.png)
有上图的数据后，

然后，首页继续找如下
![image](https://user-images.githubusercontent.com/49942853/186285367-cab101b3-314e-4c48-b84f-3e3216a64dcf.png)
上图为彩虹屁的密钥，简称：E

好~第二步，你已经完成了啊~
第三步，拉代码，拉git代码总会吧！不用我教了吧？
需要教的自行百度，谢谢

然后，打开下图文件
![image](https://user-images.githubusercontent.com/49942853/186285677-e82fa467-d7f2-47e0-9119-ae6a1e446dc7.png)

![image](https://user-images.githubusercontent.com/49942853/186287220-2c7bd63f-48a3-470a-af41-b62eb2507506.png)

![2022-08-24_080251](https://user-images.githubusercontent.com/49942853/186286890-e9c37387-4cef-4515-8180-40761fbcfca4.png)

![image](https://user-images.githubusercontent.com/49942853/186287512-907ac5a9-c691-4ac8-b6ec-68cb463349a8.png)


![image](https://user-images.githubusercontent.com/49942853/186287832-d6b6e43a-75c8-4c5c-947b-f244d6d6ed88.png)




