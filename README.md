---
微信小程序自动推送当天天气情况Java版博客地址：
http://t.csdn.cn/Fd1xt

---

# 初言
**注意事项：**

**1、此文章适合有一些经验的Java开发完成部署工作，不适合一点经验都没有的纯小白来完成启动与部署**

**2、本文章是针对网上小程序推送天气消息项目的复杂和冗余性问题的二次优化与细节调整，目的就是为了更快速与方便的进行完成私人化的部署与使用**

---

# 效果图
如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/73c2c092faf74647a4f9bc7119404559.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/91ec4ec7b5d649629e445c92a23f194a.png)

简单说明：

上图中，“朝阳” 为北京的区级单位

注意后面的是今天天气的生活指数提示

最后一句粉色字体的彩虹屁的每次发送都是随机的

---

# 教程开始：
# 1、注册微信测试账号
编辑模板，首先需要拥有自己的公众号

微信公众号链接: 
[请点击此处](https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login)

![上图点击后，微信扫码登录](https://img-blog.csdnimg.cn/af446f420b4b47af847ac29681a3585a.png)
上图点击后，微信扫码登录

然后会看到如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/f7b34ea595174dda94aa078bb2dd7019.png)
**上图中两个密钥为：appID ， appsecret 简称：A，B**
## 密钥A：appID
## 密钥B：appsecret

---
向下翻，看到如下图内容：
![在这里插入图片描述](https://img-blog.csdnimg.cn/d359ffe5cb0b489a8dba856314e3efb7.png)
按照步骤，先微信关注步骤1的公众号，关注测试用的微信号

然后就会看到步骤2出现一串id，例如如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/0bb261bda0d345e7accffc9ffea88f98.png)
**上图密钥为：微信号id，简称C**
## 密钥C：微信号ID

---
页面继续向下翻，看到如下图
![在这里插入图片描述](https://img-blog.csdnimg.cn/7fa730dc830940ad8ea33713430828dd.png)
点击上图绿色按钮

然后看到如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/08efed0367e2494597b0579dbc5010e3.png)
上图中步骤1的标题先自己随便写一个，什么都行，后面能再改

上图中步骤2的模板内容如下：
```
{{beizhu.DATA}}
{{city.DATA}} 
天气：{{tianqi.DATA}} {{fengli.DATA}} 
当前气温：{{wendu.DATA}} ({{low.DATA}} ~{{high.DATA}} ) 
注意：{{ganmao.DATA}} 
--- 手动分割 --- 
是我们在一起的第{{lianai.DATA}}天~ 
距离你的生日还有{{shengri.DATA}}天~ 
{{caihongpi.DATA}} 
```

点击上图步骤3。提交后，会看到如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2f5a0cafc4f840eca0ffa520482d2b17.png)
**上述密钥为：模板id，简称 D**
## 密钥D：模板ID
好！第一步完成

---

# 2、 彩虹屁平台注册
微信公众号链接: [请点击此处](https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login)

打开页面后如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/6f0b692202cc4d8faac2d43a7574d00e.png)
点击“申请接口”，如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/b6172f70418d4bae8bbcccf5656fd467.png)

鼠标放到1上，然后点击2的位置，如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/f24015a8e2e04208a7af0bb15e33f700.png)

然后就会看到如下：我的因为已经申请过了，你们自己申请就行了，如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/518ee477ab11479d9cca3f65b5a703ac.png)

如果申请结束后，返回首页的位置，如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/1b2110df422045e78d04b6d7922ad2c3.png)
有上图的数据后，

然后，在首页按照步骤继续找，如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/991c2e357d6646099aed20c2da22f028.png)
**上图为彩虹屁的密钥，简称：E**
## 密钥E：彩虹屁密钥
好~第二步，你已经完成了啊

---

# 3、申请天气接口（付费）
回归首页，按照步骤点击，如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/4e88c86b5d6f4ec6a1d5d740a5d84a02.png)

点击申请接口，如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/4ad7a5a86f5840dc971b8297406dfd8f.png)

**注意：点击如上的申请接口时，需要实名认证，并且付费，但10元可以用10w次，我想这个钱还是可以掏的起的吧~**

**之前的免费的公共的天气接口，因为点击次数太多，被封了，我找了好久都没有再找到免费的天气接口**

**这就是我为什么耽误这么久没改的原因**

随后点击“在线测试”，会看到如下图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/fea2ae690e184933b8bef294add3e163.png)
如果你之前成功申请后，这里就可以点击“测试请求”按钮了，随后，右下角就会返回测试数据

其中的参数需要讲解一下，

```
key:这个是你的密钥，不用管，刚刚你已经复制了密钥E，就是这里的密钥
city:这个是城市代码，你可以从百度自行搜索你所在的市县村区的编码
type:这里只有两个参数，输入1就代表只查今天的天气，输入7代表查看最近7天的天气情况
```
点击“测试请求”按钮后，如果成功返回了天气

那么ok，第三步到此结束

---
# 4、拉代码
拉git代码总会吧！不用我教了吧？ 需要教的自行百度，谢谢

```
代码地址如下
github仓库地址：
	https://github.com/jin0424/luobin_tianqituisong2.git
	
gitee仓库地址：
	https://gitee.com/Li-jinhu/luobin_tianqituisong2.git
```

# 5、修改配置信息
拉下来然后，打开下图指向的文件

![在这里插入图片描述](https://img-blog.csdnimg.cn/aea471e865224aac9740e257b9462055.png)


下图中的ABCDE 密钥不用我说了吧，把之前我说的 ABCDE 密钥都分别粘上去
![在这里插入图片描述](https://img-blog.csdnimg.cn/dd1f57f5853346ea9cf3d6f8841f60c8.png)

好了，都弄完了后，就可以开始启动了

---

# 6、启动项目试试

![在这里插入图片描述](https://img-blog.csdnimg.cn/61a1feec795a4765a29e52b42ce6e99a.png)

启动后你就可以在公众号收到消息了

---

上述的东西都没有问题了以后，就可以开始部署项目了

# 7、部署项目

![在这里插入图片描述](https://img-blog.csdnimg.cn/4cdad4be8a5c482fb715fe6e5f825b6b.png)

打包完毕后，如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/32376673199943eb8a686d2b5664b901.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/d9fafa13ea0a466b83dbb6d272d7b21f.png)

# 升级说明
时间：2022年8月23日

**初版发布**

时间：2023年2月20日

**修复天气接口bug、删除已失效的城市编码查询地址、删除颜文字等。**
