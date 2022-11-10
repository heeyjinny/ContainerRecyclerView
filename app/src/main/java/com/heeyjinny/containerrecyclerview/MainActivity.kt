package com.heeyjinny.containerrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.heeyjinny.containerrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //뷰바인딩
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뷰바인딩
        setContentView(binding.root)

        //FIN
        //생성한 레이아웃과 소스코드 연결
        //FIN 1.
        //사용할 데이터를 생성하는 코드 추가
        val data: MutableList<Memo> = loadData()

        //FIN 2.
        //어댑터를 생성하고 어댑터의 listData변수에 데이터목록(변수data) 저장
        var adapter = CustomAdapter()
        adapter.listData = data

        //FIN 3.
        //recyclerView위젯의 adapter속성에 생성한 어댑터 연결
        binding.recyclerView.adapter = adapter

        //FIN 4.
        //리사이클러뷰를 화면에 보여주는 형태를 결정하는 레이아웃 매니저 연결
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


    }//onCreate

    //MutableList<Memo>를 반환하는 loadData()메서드 생성
    //loadData()메서드 호출 시 100개의 가상 데이터를 받을 수 있음
    fun loadData(): MutableList<Memo>{

        //리턴할 MutableList컬렉션 선언하여 변수data에 담기
        val data: MutableList<Memo> = mutableListOf()

        //100개의 가상의 데이터 생성, 반복문 for문 이용
        //no변수는 그대로 Memo클래스의 번호로 사용예정
        for (no in 1..100){

            //타이틀과 날짜로 사용할 데이터를 가상으로 생성해서 변수에 담기
            //System.currentTimeMillis() : 스마트폰의 현재 시간의 숫자값
            val title = "리사이클러 뷰 목록 ${no}"
            val date = System.currentTimeMillis()

            //변수에 저장된 값과 번호로 Memo클래스 생성하여 변수 memo에 담고
            //리턴할 변수 data에 추가 (add)
            var memo = Memo(no, title, date)
            data.add(memo)

        }

        //반복문이 끝나면 100개의 Memo클래스가 담겨있는 data변수를 리턴하여 호출한 측에 전달
        return data

    }//loadData

    //리사이클러뷰어댑터를 상속받는 커스텀어댑터클래스 생성 하고 같은 파일 안에 홀더 클래스 생성
    //java디렉터리 밑 패키지 우클릭 - New - Kotlin File/Class -  CustomAdapter 클래스 생성

}//MainActivity
