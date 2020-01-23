from flask import Flask, jsonify
from flask_cors import CORS
from urllib import parse
from selenium import webdriver
import time

app = Flask(__name__)
cors = CORS(app, resources={r"*": {"origins": "*"}})


@app.route("/")
def index():
    return "hello"


@app.route("/instagram/<tag>")
def naver_crawling(tag):
    url_tmp = "www.instagram.com/explore/tags/"+tag
    url = "https://" + parse.quote(url_tmp)
    driver = webdriver.Chrome(executable_path="./chromedriver.exe")
    driver.get(url)
    time.sleep(5)

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

    driver.close()

    return jsonify(list)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
