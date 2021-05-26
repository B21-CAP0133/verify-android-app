package com.b21cap0133.verify.messaging

import android.view.View
import com.b21cap0133.verify.R
import com.b21cap0133.verify.databinding.ReceiveItemBinding
import com.b21cap0133.verify.model.Message
import com.xwray.groupie.viewbinding.BindableItem

class ReceiveItems(private val message: Message): BindableItem<ReceiveItemBinding>() {
    override fun initializeViewBinding(view: View): ReceiveItemBinding {
        return ReceiveItemBinding.bind(view)
    }

    override fun bind(viewBinding: ReceiveItemBinding, position: Int) {
        viewBinding.sendText.text = message.content
    }

    override fun getLayout(): Int {
        return R.layout.receive_item
    }
}