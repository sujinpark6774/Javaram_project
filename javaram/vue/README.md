# 자바람개비 프론트엔드 레퍼지토리

## 1. 관련 문서
- [구글드라이브 링크](https://drive.google.com/drive/folders/1E3WrgNbrxduoKyHWEFAxzDygCwxm4JE5)

## 2. 진행 예정인 사항
|일정|할일|인원|
|------|---|---|
|~3/16 (수) | 공통 화면(로그인, 메인) 내 이슈 해결 및 리뷰 예정|박수진, 신현아, 임지호|
|~3/18일 (금)| 공통/파트별(로그인,메인) 프론트 완료 |프로젝트 인원 전체|
|~4/1 (금) | 파트별 백엔드 완료 |프로젝트 인원 전체|

## 3. 형상 관리 관련 팁, 방안
- 하나의 기능 단위 개발이 끝나면, main 브랜치와 병합한다.
##### 1. main Branch에서 새로 브랜치를 판다.(맨 처음 클론한 다음)
    git  checkout -b <앞으로 쓸 본인 브랜치 명> 
##### 2.본인 Branch에서 개발
    git add <본인브랜치>
    git commit -m "커밋 메시지"
    git push origin <본인 브랜치명> (선택사항)
##### 3.기능 단위 개발완료 이후 main 브랜치와 병합
    git checkout main # main브랜치로 Checkout
    git pull origin main #본인 브랜치를 Merge -> main브랜치에 반영
    git merge <본인 브랜치> #본인 브랜치 main브랜치와 동기화
    git commit -m "커밋 메시지"
    git push origin main
##### 4.main Branch 에서 Pull(머지한 이후 개발할 경우)
    git pull origin main # 원격에 있는 main 브랜치 변동사항 -> 로컬에 반영
    git checkout <본인 브랜치> # 본인 브랜치로 이동
    git merge main #최신화된 로컬의 main 브랜치와 동기화
  
- Gitlab 사용시 몇가지 팁
##### 1. 로컬의 git 계정 정보 수정
    git config --global user.name "Your Name"
    git config --global user.email you@example.com
##### 2. 클론시 본인계정명, Gitlab IP/포트번호 기입
    http://<본인계정명>@<GitLaIP>:<GitLab포트번호>/sung5.ahn/javaram-fontend.git
    
## 4. 소스코드 관리 방안
- 공통 모듈, 기능별 모듈로 관리를 한다.(Depth1)
- 각각의 대분류는 이후 페이지와 컴포넌트로 나뉜다(Depth2) 

|Depth1|Depth2|SourceCode|
|------|---|---|
| 기능별 모듈 | Page |<페이지명>.vue|
|  | Component|<컴포넌트명>.vue|
| Common(공통모듈)|| Page |<페이지명>.vue|
       |  | Component|<컴포넌트명>.vue|
       
- 현재 소스 디렉터리
    - vue/src/components 하위 디렉터리에 기능단위 디렉터리와 *.vue파일을 위치시킴.
    - 주요 디렉터리는 다음과 같다
    
|Depth1|Depth2|Description|
|------|---|---|
| refs | Page |레퍼런스로 쓰는 페이지|
|  | Component| 레퍼런스로 쓰는 컴포넌트|
| Common | Page | 공통으로 사용하는 페이지|
|       |   Component|공통으로 쓰는 컴포넌트|