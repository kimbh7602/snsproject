from flask import Flask, jsonify
from flask_cors import CORS
from urllib import parse
from selenium import webdriver
from selenium.webdriver import ActionChains
import time
import requests

app = Flask(__name__)
cors = CORS(app, resources={r"*": {"origins": "*"}})


@app.route("/")
def index():
    return "hello"


@app.route("/instagram/<tag>")
def naver_crawling(tag):
    url_tmp = "www.instagram.com/explore/tags/" + tag
    url = "https://" + parse.quote(url_tmp)
    driver = webdriver.Chrome(executable_path="./chromedriver.exe")
    driver.get(url)
    time.sleep(2)

    divs = driver.find_elements_by_css_selector("div.v1Nh3.kIKUG._bz0w")

    list = []

    for div in divs:
        href = div.find_element_by_css_selector("a").get_attribute("href")
        img_url = div.find_element_by_css_selector("img").get_attribute("src")
        img = {
            'href': href,
            'img_url': img_url
        }
        list.append(img)
        if(len(list) == 6):
            break;

    driver.close()

    result = {
        'tag': tag,
        'list': list
    }

    return jsonify(result)


@app.route("/location/<keyword>")
def search_location(keyword):
    url = 'https://maps.googleapis.com/maps/api/place/textsearch/json?key=AIzaSyAcnkt6IBUt-bGIMw4u-VEIYpesgw4-2Lk&query=' + parse.quote(
        keyword)

    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36',
        'Referer': 'https://maps.googleapis.com'
    }

    html = requests.get(url, headers=headers).text

    # print(jsonify(html))

    return html

@app.route("/searchByKeyword/<keyword>")
def search_by_keyword(keyword):
    url = "https://www.twinword.com/writer/"

    driver = webdriver.Chrome(executable_path="./chromedriver.exe")
    driver.get(url)
    # time.sleep(3)

    div = driver.find_element_by_css_selector("div.redactor-editor")
    driver.execute_script("arguments[0].innerHTML = '"+keyword+"'", div)
    # driver.execute_script("arguments[0].setAttribute('class', 'asdlknsvklsn')", div)
    actions = ActionChains(driver)
    actions.move_by_offset(125, 255)
    actions.click()
    # time.sleep(1)
    actions.click()
    actions.click()
    actions.click()
    actions.perform()
    time.sleep(1)
    suggestions = driver.find_elements_by_css_selector("div.dropDownBox_suggestion")
    results = []
    results.append(keyword)
    for suggestion in suggestions:
        results.append(suggestion.text)
        # if(len(results)>=10):
        #     break
    print(results)
    driver.close()
    # print(div.text)

    return jsonify(results)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
