### Python 설치
- 참조 :: <https://letsrt.com/5724>
<hr>


#### 1. 의존성 설치
```shell
$> su
$> dnf -y update
$> dnf -y install wget yum-utils make gcc openssl-devel bzip2-devel libffi-devel zlib-devel
```


#### 2. 다운로드 및 설치
- 다운로드주소 <https://www.python.org/downloads/source/>

```shell
$> cd /usr/local/src
$> wget https://www.python.org/ftp/python/3.9.2/Python-3.9.2.tgz
$> tar xzf Python-3.9.2.tgz

$> cd Python-3.9.2
$> ./configure --with-system-ffi --with-computed-gotos --enable-loadable-sqlite-extensions
$> make altinstall
```


#### 3. 버전확인
```shell
$> python3.9 -V
```


#### 4. 별칭설정
```shell
$> alias py39=python3.9
```
