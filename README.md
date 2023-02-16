# easytask

전체적인 서비스가 아닌 리뷰 시스템에 포커싱을 맞춘 프로젝트

---

## 이지태스크 서비스

- 이지태스크는 고객과 프리랜서 (“이루미"로 칭함)로 나누어져 있음
- 고객은 업무를 맡길 수 있음(등록)
- 이루미는 등록된 업무 중 하나와 자동매칭이 됨
- 이루미는 업무가 끝났으면 마이페이지에서 업무종료 버튼을 누름
- 고객은 업무 종료 후 이루미에 대한 리뷰를 남길 수 있음

---

## 이지태스크 리뷰 프로젝트 흐름

1. 유저생성
2. 업무등록
3. 이루미 업무 매칭 수락
4. 업무완료
5. 완료된 업무에 관한 리뷰 등록

---

## 엔티티 관계도

![image](https://user-images.githubusercontent.com/104514223/218963186-e4c7c8c0-0e77-4585-97a2-e0cb4e9dd5b5.png)

### user

- 회원의 기본 정보를 담은 테이블

### signtask

- 고객이 등록한 업무에 대한 정보를 담은 테이블
- 업무 등록시 세부역량 설정 가능
  - 전문역량 세부 설정 정보는 professionalskill 테이블에 들어감
  - 프로그램역랭 세부 설정 정보는 programskill 테이블에 들어감

### completetask

- 업무가 완료 되었을 때 관련 정보를 담은 테이블
  - 소요시간, 지불금액 등
  - completetask가 생성될 때 관련 signtask의 상태가 INACTIVE로 변경 됨

### review

- 이루미의 리뷰에 대한 정보를 담은 테이블
  - 리뷰는 고객이 이루미에 대해서만 할 수 있음
  - 리뷰는 completetask의 ID 값을 통해서만등록할 수 있음
- completetask의 ID값을 통해 고객이 설정한 세부역량들을 평가할 수 있음
  - professionalskillrating테이블은 전문역량의 세부사항들에 대한 점수를 담음
  - programskillrating테이블은 프로그램역량의 세부사항들에 대한 점수를 담음

---

## 프로젝트 구조

- 'common' : 메인 로직은 아니지만 예외처리, baseresponse를 담은 폴더

- 'controller' : 각 도메인의 Request를 받고 Response 해주는 곳

  - 'requestDTO' : 요청 형식 관련 폴더
  - 'responseDTO' : 응답 형식 관련 폴더

- 'service' : 각 도메인의 비즈니스 로직 처리 폴더

  - Validation 구현 전

- 'entity' : 엔티티를 관련 폴더

- 'repository' : Spring Data JPA Interface를 상속받아 DB 처리를 가능하게 해준다.
