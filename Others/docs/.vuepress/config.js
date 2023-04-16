module.exports = {
  port: '8083',
  lang: 'zh-CN',
  title: 'MISP-Docs',
  description: 'MISP项目文档',

  themeConfig: {
    logo: 'https://vuejs.org/images/logo.png',
    nav: [
      { text: '首页', link: '/' },
      { text: '快速上手', link: '/guide/' },
    ],
    sidebar: 'auto',
    sidebarDepth: 2,
  },
}