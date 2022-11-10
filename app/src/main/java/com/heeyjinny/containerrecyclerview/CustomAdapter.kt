package com.heeyjinny.containerrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.heeyjinny.containerrecyclerview.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

//리사이클러뷰는 리사이클러뷰어댑터라는 메서드 어댑터를 사용해 데이터 연결
//리사이클러뷰어댑터는 개별 데이터에 대응하는 뷰홀더 클래스 사용함
//그래서 상속하는 리사이클러뷰어댑터에 뷰홀더 클래스를 제네릭으로 지정해야 하므로
//뷰홀더 클래스를 먼저 만들고 나서 어댑터 클래스를 생성하는 것이 편리함

//2.
//어댑터 클래스 코드인 CustomAdapter클래스는
//RecyclerView의 Adapter를 상속받고 앞에서 생성한 Holder를 제네릭<>으로 지정하고
//어댑터에 설계되어있는 3개의 인터페이스 구현 필수
class CustomAdapter: RecyclerView.Adapter<Holder>() {

    //3.
    //이 어댑터에서 사용할 데이터 목록 변수 선언
    //미리 작성해둔 loadData()함수에서의 리턴값을 사용하여
    //나중에 listData 변수를 메인 액티비티에서 직접 호출해 값을 넣을 것이기 때문에
    //mutableListOf<Memo>() 사용
    var listData = mutableListOf<Memo>()

    //onCreateViewHolder(): 스마트폰 한 화면에 그려지는 아이템 개수만큼 레이아웃 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //5.
        //액티비티와 다르게 어댑터에서는 바인딩(ItemRecyclerBinding)의 inflate메서드는 3개의 파라미터 사용
        //inflate(inflater, parent, attatchToRoot)
        //inflater: 바인딩 생성 시 사용, 액티비티와 다르게 LayoutInflater.from 사용하고
        //파라미터로 context전달 시 안드로이드가 넘겨주는 parent에서 꺼내서 사용
        //parent: 생성되는 바인딩이 속하는 부모 뷰(레이아웃)
        //attatchToRoot: 항상false(뷰의 최상위 레이아웃의 속성이 기본적용), true일 경우 attatch해야하는 대상 root지정해야함
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        //6.
        //생성된 바인딩을 Holder클래스에 담아서 반환
        return Holder(binding)
    }

    //onBindViewHolder(): 생성된 아이템 레이아웃에 값 입력 후 목록에 출력
    override fun onBindViewHolder(holder: Holder, position: Int) {
        //7.
        //생성된 뷰홀더를 화면에 보여줌
        //먼저 listData에서 현재 위치에 해당하는 메모를 하나 꺼내 memo변수에 저장 후 홀더에 전달
        //임의로 홀더에 setMemo()라는 메서드가 있다고 가정하여 구형
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    //getItemCount(): 목록에 보여줄 아이템 개수
    override fun getItemCount(): Int {
        //4.
        //리사이클러뷰에서 사용할 데이터의 총 개수를 리턴하는 메서드 구현
        return listData.size
    }
}

//1.
//먼저 홀더 클래스 생성
//뷰홀더 클래스는 현재 화면에 보여지는 개수만큼만 생성되고 스크롤 될 경우
//뷰홀더를 재사용한 후 데이터만 바꿔주어 앱 효율이 향상됨

//RecyclerView의 ViewHolder를 상속받음
//상속 받을 때 생성자에 1개의 값이 필수로 입력되어야 함

//ViewHolder 클래스 생성자에는 다음에 만들 어댑터의 아이템 레이아웃을 넘겨줘야 하므로
//Holder 클래스를 생성할 때 생성자에게서 레이아웃의 바인딩을 넘겨 받아야 함

//생성자에 입력해야 할 아이템 레이아웃은 ViewHolder자체에서 만들지 않고 어댑터가 만들어서 넘겨줌
//어댑터에서 넘겨주는 바인딩을 Holder클래스의 생성자에게서 받아 ViewHolder의 생성자에게로 넘겨주는 구조
//ViewHolder의 생성자는 바인딩이 아닌 그 자체의 View를 필요로 하기 때문에 root전달
//바인딩은 Holder클래스 안에서 전역변수로 사용되므로 val로 변수 생성해야함
class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
    //8.
    //화면에 데이터를 세팅하는 setMemo()메서드 구현
    fun setMemo(memo: Memo){
        //9.
        //위젯과 메모데이터 연결
        binding.textNo.text = "${memo.no}"
        binding.textTitle.text = memo.title
        //날짜 값을 날짜 형식으로 변환: SimpleDateFormat사용(java.text)
        var sdf = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
        var formattedDate = sdf.format(memo.timestamp)
        binding.textDate.text = formattedDate
    }

    //10. 목록 클릭 이벤트처리
    //홀더가 가지고 있는 아이템뷰에 클릭 리스너를 달아 목록 클릭 시 실행
    //홀더 클래스가 생성되는 시점에 클릭리스너 추가 init추가하여 작성
    init {
        binding.root.setOnClickListener {
            //목록 클릭 시 해당 내용 토스트로 보여줌
            Toast.makeText(binding.root.context, "선택한 아이템 : " + binding.textTitle.text,
                            Toast.LENGTH_SHORT).show()
        }
    }

}

//10. 지금까지 생성한 레이아웃과 소스코드를 MainActivity.kt에서 모두 연결