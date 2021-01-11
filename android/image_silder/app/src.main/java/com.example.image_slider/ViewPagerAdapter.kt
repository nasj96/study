package com.example.image_slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ViewPagerAdapter(private val context: Context): PagerAdapter() {
    val Image = arrayOf(
        R.drawable.ai,
        R.drawable.css,
        R.drawable.html,
    )

    private var layoutInflater:LayoutInflater? = null

    override fun getCount(): Int {
        // 뷰페이지 전체 페이수 결정
        return Image.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        // 뷰페이지가 키 객체와 연관되는지 확인
        // instantiateItem 에서 객체를 리턴하고, isViewFromObject 에서 동일여부 결과 리턴
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // 포지션 위치의 페이지 생성
        //return super.instantiateItem(container, position)

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.viewpager_activity, null)
        val image = v.findViewById<View>(R.id.imageview) as ImageView

        image.setImageResource(Image[position])

        val vp = container as ViewPager
        vp.addView(v, 0)

        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // 포지션 위치의 페이지 제거
        //super.destroyItem(container, position, `object`)

        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}