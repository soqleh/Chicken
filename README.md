# Chicken 배달 프로그램
## 다이어그램
---
<img src="https://github.com/soqleh/Chicken/blob/main/img/diagram.png" width="100%" height="100%" alt=”diagram.png”></img>
---
## Class 설명
### OrderSystem
- main함수를 가지고 있어 전체적인 flow를 담당
- LoginManager를 통해 현재 로그인 된 사용자의 정보(id/pw 제외) 및 Login상태를 UserAccess에 전달한다.
- 현재 접속한 사용자가 Guest인지 Owner인지에 따라 다른 메뉴 flow를 제공한다.

### LoginManager (singleton)
- 사용자 정보를 입력받아 회원가입, 로그인 등을 진행한다.
- ID/PW를 잘못된 format으로 입력하지 않도록 제어한다.
- 로그인 정보를 LoginInfo 클래스에 담아 HashMap에 저장하고 최소한의 정보를 TransferableLoginIfo에 담아 OrderSystemp에 전달한다.

### LoginInfo
- Login에 필요한 id, pw, user이름, userType등을 저장는 데이터클래스로 로그인을 시도하는 User의 data와 비교하도록 한다.

### TransferableLoginInfo
- LoginInfo의 Data중에 id, pw를 제외한 정보를 OrderSystem에 넘긴다.

### IActionInterface
- Owner나 Guest가 해야하는 일에 대한 함수 signature를 담고 있다.

### User
- 사용자의 가장 상위 class로 Owner와 Guest가 이를 상속받는다.
- UserAccess는 하우 Class가 아닌 상위 객체인 User Class만 바라보는 형태로 구현함.

### Owner
- User를 상속받은 가맹주들에 대한 클래스
- Owner가 진행해야 하는 일들이 구현되어 있다.
- 프로그램 구현 복잡도를 줄이기 위해 Owner들은 하나의 상태값만을 갖도록 singlton으로 처리했다 (Owner별 별도 처리 안함)

### Guest
- User를 상속받은 손님들에 대한 클래스
- User가 진행해야 하는 일들이 구현되어 있다.
- 로그인 테스트나 여러 User가 별점을 주었을 때 평균값이 반영되도록 처리하고 싶어 각각의 Guest가 각각의 상태값을 갖도록 처리했다. 
 (Guest별로 각각의 주문 내역 보유)

### Menu
- 가게에 등록된 음식에 대한 Class로 가게 이름, 가격, 별점등의 정보를 갖는다.
- Guest나 Owner에서 주문을 하거나 Menu를 등록할 때 Menu객체를 생성해서 TotalOrderDB나 TotalStoreDBRepository에 넣는다.

### TotalOrderDBRepository (singleton)
- 모든 주인이 등록한 가게 및 메뉴 정보를 저장하는 DB경 DB Accessor역할.
- 해당 프로젝트에서 DB를 사용하지 않았으므로 DB와 같은 효과를 주도록 singletone으로 임시 처리하였다.

### TotalOrderDBRepository (singleton)
- 모든 고객이 주문한 주문 내역을 저장하는 DB경 DB Accessor역할.
- 해당 프로젝트에서 DB를 사용하지 않았으므로 DB와 같은 효과를 주도록 singletone으로 임시 처리하였다.

### UserType
- final static int 타입의 상수들을 선언한 클래스
- 손님인지 주인인지에 대한 상수값.

### ActionType
- final static int 타입의 상수들을 선언한 클래스
- 손님과 주인이 처리할 수 있는 사용자 동작이 다른데 index로 입력받기 때문에 편하게 매핑하기 위한 상수값
- 메뉴 등록하기, 별점 주기, 별점 보기 등의 상수값

### LoginType
- final static int 타입의 상수들을 선언한 클래스
- 로그인, 회원가입 처리 시 index 입력값과 편하게 매핑하기 위한 상수값
- 로그인하기, 회원가입 하기 등의 상수값

