### Python 설치
- 참조 :: <https://ichi.pro/ko/ubuntu-20-04ui-soseueseo-python-bildeu-51482144281899>
<hr>


#### 1. 의존성 설치
```shell
$> su
$> apt-get update
$> apt-get install -y build-essential checkinstall
$> apt-get install -y libreadline-gplv2-dev libncursesw5-dev libssl-dev libsqlite3-dev tk-dev libgdbm-dev libc6-dev libbz2-dev
```


#### 2. 다운로드 및 설치
- 다운로드주소 <https://www.python.org/downloads/source/>

```shell
$> cd /usr/local/src
$> wget https://www.python.org/ftp/python/3.8.7/Python-3.9.2.tgz
$> tar xzf Python-3.9.2.tgz

$> cd Python-3.9.2
$> ./configure --enable-optimizations
$> make altinstall
```


#### 3. 버전확인
```shell
$> python3.9 -V
$> python3 -V
```


#### 4. 별칭설정
```shell
$> alias py39=python3.9
```
