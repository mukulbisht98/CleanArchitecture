package com.xxmukulxx.notes.feature_profile.presentation.vm

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.common.presentation.adapter.RecyclerAdapter
import com.xxmukulxx.notes.databinding.FragProfileBinding
import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_profile.domain.model.Product
import com.xxmukulxx.notes.feature_profile.domain.model.ProfileContent
import com.xxmukulxx.notes.feature_profile.presentation.adapter.ProfileContentAdapter
import com.xxmukulxx.notes.util.capitalize
import com.xxmukulxx.notes.util.getString
import com.xxmukulxx.notes.util.setImgProfile
import com.xxmukulxx.notes.util.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(userUseCases: UserUseCases) :
    BaseViewModel() {

    lateinit var b: FragProfileBinding
    lateinit var mainFragment: MainFragment

    fun setAppBar() {
        mainFragment.setAppBar(getString(R.string.profile))
    }

    fun setProfilePic() {
        b.ivProfilePic.setImgProfile("https://picsum.photos/200/200")
    }

    fun setRecyclerViews() {
        b.rvProfileOptions.apply {
            val list = mutableListOf(
                "Your Order",
                "Buy Again",
                "Your Account",
                "Your Wishlist"
            )
            adapter = RecyclerAdapter(
                list, R.layout.item_profile_options
            ) {
                toast(list[it])
            }.apply {
                isAnimation = true
            }
            layoutManager = GridLayoutManager(context, 2)
        }
        b.rvProfileContent.apply {
            val list = mutableListOf(
                ProfileContent(
                    "Your Orders",
                    mutableListOf(Product("Cube"), Product("computer"), Product("ps5"), Product("4x4"), Product("rog g15"))
                ),
                ProfileContent(
                    "Recently Viewed",
                    mutableListOf(Product("laptop"), Product("iphone"), Product("Cube"))
                ),
                ProfileContent(
                    "Your Wishlist",
                    mutableListOf(Product("car"), Product("bike"), Product("aeroplane"))
                ),
                ProfileContent(
                    "Your Account",
                    mutableListOf(Product("address"), Product("mobile number"), Product("payments"))
                ),
            )
            adapter = ProfileContentAdapter(list)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private val profileData: UserData? = userUseCases.getUser()
    val email = profileData?.user?.email
    val name = profileData?.user?.name?.capitalize()
    val greetings = "Hello, $name"
}