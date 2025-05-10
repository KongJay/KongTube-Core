#KongHub
1.注册实现
api:/web/account/register
  1.validation 校验邮箱、昵称、密码、验证码
  2.校验验证码
  3.校验邮箱和昵称是否已注册
  4.存储用户信息
2.登录实现：
api:/web/account/login
  1.validation 校验邮箱、昵称、密码、验证码
  2.校验验证码
  3.获取ipAddr
  4.验证账号、密码以及账号状态
  5.更新登录时间、登录ip
  6.生成token，将tokenUserInfo存储到redis中
  7.将token存储到Cookies中

- TokenUserInfoDto：@JsonIgnoreProperties(ignoreUnknown = true) 忽略多余字段


