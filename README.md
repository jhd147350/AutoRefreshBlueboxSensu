# AutoRefreshBlueboxSensu  
自动化监控sensu中的工单，有新工单自动播放MP3（contra.mp3）   
只统计存在时间大于2分钟的工单  
默认刷新间隔75s  
账号和密码 通过启动参数传入  
自动等待yubi key的输入  
不能暂停播放MP3，只能关闭程序  
重新开启程序前，最好注销之前的登录

运行程序后一定要聚焦到打开的网页，只有聚焦在打开的网页才能顺利的 利用java模拟ctrl + t 打开新标签页，才能继续执行程序。  

**绝对不能放在Remedy监控工具后面运行，没想到当时为了节约内存，在remedy那个程序中执行了一个杀掉所有chromedrive.exe的命令，然后在启动一个新的chromedriver.exe。如果先运行此程序，再执行remedy的自动化程序，会将本程序的chromedriver杀掉，导致程序异常**
