package com.heeyjinny.containerrecyclerview

//아이템 레이아웃 1개에 3개의 데이터 출력하므로 String사용하지 않음
//String은 단일 값을 담는 데이터 타입
//번호, 타이틀, 날짜 세 종류의 값을 담을(파라미터 3개) 데이터 클래스 생성
//날짜는 실제 개발할 때도 타임스탬프라고 불리는 숫자형을 저장해 변환해서 많이 사용함
data class Memo(var no: Int, var title: String, var timestamp: Long)

//data클래스인 Memo클래스 틀에 맞춰 MainActivity.kt 안에 100개의 가상 데이터를 만드는 코드 작성
