<<<<<<< HEAD
관통 프로젝트 제출 방법

# 관통프로젝트: TP_S10_S09_Framework_HappyHouse_조형준_박이언_김예림 
### 제출일: 2024.11.03

### 참여 페어
- 조형준(조장), 박이언, 김예림

### 처리된 요구사항 목록
 
|난이도|구현기능|세부|작성여부(O/X)|
|:---:|---|---|:---:|
|기본|메인페이지||O|
|기본|회원관리 페이지|회원정보등록/수정/삭제/검색|O|
|기본|로그인/로그아웃 페이지||O|
|기본|실거래가 검색,결과 페이지|전체검색,상세검색,동별, 아파트별 검색화면|O|
|추가|비밀번호 찾기/사이트맵/메뉴구성||X|
|추가|관심지역 동네 업종 정보, 관심지역 대기오염 정보||X|
|심화|웹사이트 소개/공지사항 관리 화면||△|


 
<span style="color:red">
* 작성된 기능은 반드시 캡쳐되어야 합니다.<br>
* 추가로 구현한 기능을 표에 추가시키세요.
</span>

### 실행화면 캡쳐 -
TODO: 요구사항 목록에서 완료 처리된 사항의 캡쳐 이미지를 등록하세요.

DB 구축 : select한 결과를 캡쳐해 주세요

#### 구현 기능: 메인 페이지
![실행화면캡쳐](https://lab.ssafy.com/yerimin1999/tp_s10_s09_framework_happyhouse_CHJ_PYI_KYR/-/raw/master/img/main.PNG)

#### 구현 기능: 회원관리 페이지 [회원 정보 등록 / 수정 / 삭제 / 비밀번호 변경]
##### 등록
![실행화면캡쳐](https://lab.ssafy.com/yerimin1999/tp_s10_s09_framework_happyhouse_CHJ_PYI_KYR/-/raw/master/img/join.gif)
##### 수정/삭제
![실행화면캡쳐](https://lab.ssafy.com/yerimin1999/tp_s10_s09_framework_happyhouse_CHJ_PYI_KYR/-/raw/master/img/mypage_modify.gif)
##### 변경
![실행화면캡쳐](https://lab.ssafy.com/yerimin1999/tp_s10_s09_framework_happyhouse_CHJ_PYI_KYR/-/raw/master/img/password_modify.gif)

#### 구현 기능: 로그인/로그아웃 페이지
![실행화면캡쳐](https://lab.ssafy.com/yerimin1999/tp_s10_s09_framework_happyhouse_CHJ_PYI_KYR/-/raw/master/img/login_logout.gif)

#### 구현 기능: 실거래가 검색,결과 페이지
![실행화면캡쳐](https://lab.ssafy.com/yerimin1999/tp_s10_s09_framework_happyhouse_CHJ_PYI_KYR/-/raw/master/img/sales.gif)

#### 구현 기능: 공지사항
![실행화면캡쳐](https://lab.ssafy.com/yerimin1999/tp_s10_s09_framework_happyhouse_CHJ_PYI_KYR/-/raw/master/img/notice.gif)
=======
# TP_S10_S09_Spring_HappyHouse_조형준_박이언_김예림



## Getting started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

Already a pro? Just edit this README.md and make it your own. Want to make it easy? [Use the template at the bottom](#editing-this-readme)!

## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin https://lab.ssafy.com/ryuu.public/tp_s10_s09_spring_happyhouse.git
git branch -M master
git push -uf origin master
```

## Integrate with your tools

- [ ] [Set up project integrations](https://lab.ssafy.com/ryuu.public/tp_s10_s09_spring_happyhouse/-/settings/integrations)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Set auto-merge](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing(SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

# Editing this README

When you're ready to make this README your own, just edit this file and use the handy template below (or feel free to structure it however you want - this is just a starting point!). Thank you to [makeareadme.com](https://www.makeareadme.com/) for this template.

## Suggestions for a good README
Every project is different, so consider which of these sections apply to yours. The sections used in the template are suggestions for most open source projects. Also keep in mind that while a README can be too long and detailed, too long is better than too short. If you think your README is too long, consider utilizing another form of documentation rather than cutting out information.

## Name
Choose a self-explaining name for your project.

## Description
Let people know what your project can do specifically. Provide context and add a link to any reference visitors might be unfamiliar with. A list of Features or a Background subsection can also be added here. If there are alternatives to your project, this is a good place to list differentiating factors.

## Badges
On some READMEs, you may see small images that convey metadata, such as whether or not all the tests are passing for the project. You can use Shields to add some to your README. Many services also have instructions for adding a badge.

## Visuals
Depending on what you are making, it can be a good idea to include screenshots or even a video (you'll frequently see GIFs rather than actual videos). Tools like ttygif can help, but check out Asciinema for a more sophisticated method.

## Installation
Within a particular ecosystem, there may be a common way of installing things, such as using Yarn, NuGet, or Homebrew. However, consider the possibility that whoever is reading your README is a novice and would like more guidance. Listing specific steps helps remove ambiguity and gets people to using your project as quickly as possible. If it only runs in a specific context like a particular programming language version or operating system or has dependencies that have to be installed manually, also add a Requirements subsection.

## Usage
Use examples liberally, and show the expected output if you can. It's helpful to have inline the smallest example of usage that you can demonstrate, while providing links to more sophisticated examples if they are too long to reasonably include in the README.

## Support
Tell people where they can go to for help. It can be any combination of an issue tracker, a chat room, an email address, etc.

## Roadmap
If you have ideas for releases in the future, it is a good idea to list them in the README.

## Contributing
State if you are open to contributions and what your requirements are for accepting them.

For people who want to make changes to your project, it's helpful to have some documentation on how to get started. Perhaps there is a script that they should run or some environment variables that they need to set. Make these steps explicit. These instructions could also be useful to your future self.

You can also document commands to lint the code or run tests. These steps help to ensure high code quality and reduce the likelihood that the changes inadvertently break something. Having instructions for running tests is especially helpful if it requires external setup, such as starting a Selenium server for testing in a browser.

## Authors and acknowledgment
Show your appreciation to those who have contributed to the project.

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
>>>>>>> c995aa6da5d63710881059145e8454c04ab28bd1
