package com.example.sun29.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.sun29.R
import com.example.sun29.data.room.SunUser
import kotlinx.android.synthetic.main.fragment_two.*
import kotlinx.android.synthetic.main.sun_cell.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TwoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /**
         * 启动 sunshimmer 图片闪动加载
         */
        sunshimmer.apply {
            setShimmerColor(0x55FFFFFF)  //设置颜色
            setShimmerAnimationDuration(1000) //设置微光动画持续时间，毫秒
            setShimmerAngle(30)  //设置倾斜角度，-45 至 45
            startShimmerAnimation() // 启动Shimmer
        }
        /**
         * 获取从 SunPageListAdapter 传过来的 对象
         */
        var imgUrl = arguments?.getParcelable<SunUser>("SUNA")
        /**
         * 这里用的是 requireContext()，如果在Activity 中使用this
         */
        Glide.with(requireContext())
            .load(imgUrl?.picture) //从对象中取图片值
            .placeholder(R.drawable.ic_android_black_24dp)
            .listener(object : RequestListener<Drawable> {  //说明：这里的 Drawable 固定就好，一个类
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    /**
                     * 图片加载失败时执行这里
                     */
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    /**
                     * 图片加载成功时执行这里
                     */
                    return false.also {
                        /**
                         * 图片加载完成后，关闭图片闪动
                         */
                        sunshimmer?.stopShimmerAnimation()
                    }
                }
            })
            .into(sunimageView)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}