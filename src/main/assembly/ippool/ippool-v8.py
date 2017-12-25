import requests
import json
import time
from lxml import etree


dealUrl = "http://localhost:8080/ippool/ipcrawler"


headers = {
    "Host": "localhost",
    "Content-Type": "application/json"
}

def post_ip(ip,port):
    url = dealUrl
    s = json.dumps({'ip': ip, 'port': port})
    r = requests.post(url, data=s,headers=headers)
    print r.text


def getHtmlTree(url, **kwargs):
    header = {'Connection': 'keep-alive',
              'Cache-Control': 'max-age=0',
              'Upgrade-Insecure-Requests': '1',
              'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko)',
              'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
              'Accept-Encoding': 'gzip, deflate, sdch',
              'Accept-Language': 'zh-CN,zh;q=0.8',
              }
    # delay 2s for per request
    time.sleep(2)

    html = requests.get(url=url, headers=header).content
    return etree.HTML(html)

url = "http://www.goubanjia.com/free/gngn/index{page}.shtml"
for page in range(1, 1000):
    page_url = url.format(page=page)
    tree = getHtmlTree(page_url)
    proxy_list = tree.xpath('//td[@class="ip"]')
    xpath_str = """.//*[not(contains(@style, 'display: none'))
                                and not(contains(@style, 'display:none'))
                                and not(contains(@class, 'port'))
                                ]/text()
                        """
    for each_proxy in proxy_list:
        try:
            ip_addr = ''.join(each_proxy.xpath(xpath_str))
            port = each_proxy.xpath(".//span[contains(@class, 'port')]/text()")[0]
            post_ip(ip=ip_addr,port=port)
            print '{}:{}'.format(ip_addr, port)
        except Exception as e:
            pass



