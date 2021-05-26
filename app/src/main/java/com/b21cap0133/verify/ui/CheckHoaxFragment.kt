package com.b21cap0133.verify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0133.verify.databinding.FragmentCheckHoaxBinding
import com.b21cap0133.verify.messaging.ReceiveItems
import com.b21cap0133.verify.messaging.SendItems
import com.b21cap0133.verify.model.Message
import com.b21cap0133.verify.ui.checkhoax.CheckHoaxViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckHoaxFragment : Fragment() {

    private val adapter = GroupAdapter<GroupieViewHolder>()
    private var binding : FragmentCheckHoaxBinding? = null
    private val viewBind get() = binding as FragmentCheckHoaxBinding
    private lateinit var recyclerView: RecyclerView
    private val checkViewModel: CheckHoaxViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = viewBind.chatRecyclerView
        recyclerView.adapter = adapter
        initialMessage()
        viewBind.buttonSend.setOnClickListener {
            val user = viewBind.textInput.text.toString()
            val message = Message("me", user)
            val send = SendItems(message)
            adapter.add(send)
            viewBind.textInput.text.clear()
            receiveAutoResponse(user)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCheckHoaxBinding.inflate(inflater, container, false)
        return viewBind.root
    }

    //this is demo with github api will change later
    private fun receiveAutoResponse(user: String) {
        val pattern = Regex("\\s+")
        val normalized = pattern.split(user)
        if (normalized.size > 1){
            if (normalized[0] == "!search"){
                val data = checkViewModel.getResult(normalized[1])
                data.observe(viewLifecycleOwner, {
                    if (it.username != "notfound404"){
                        var content = "We found ${it.username}"
                        content += if (it.name.isNullOrBlank()){
                            ", but we don't know their name"
                        }else{
                            ", their name is ${it.name}"
                        }
                        content += if (it.company.isNullOrBlank()){
                            ", we don't have their company information"
                        }else{
                            ", and they works at ${it.company}"
                        }
                        content += if (it.location.isNullOrBlank()){
                            " and finally they doesn't share their location information."
                        }else{
                            ", and finally he is currently at ${it.location} region."
                        }
                        val receive = Message("server", content)
                        val receiveItem = ReceiveItems(receive)
                        adapter.add(receiveItem)
                    }else{
                        val content = "We did not find anything"
                        val receive = Message("server", content)
                        val receiveItem = ReceiveItems(receive)
                        adapter.add(receiveItem)
                    }
                    //kill observable so no double/triple/multiple response
                    data.removeObservers(viewLifecycleOwner)
                })
            }else{
                val receive = Message("server", "unknown syntax")
                val receiveItem = ReceiveItems(receive)
                adapter.add(receiveItem)
            }
        }else{
            val data = checkViewModel.getResult(user)
            data.observe(viewLifecycleOwner, {
                if (it.username != "notfound404"){
                    var content = "We found ${it.username}"
                    content += if (it.name.isNullOrBlank()){
                        ", but we don't know their name"
                    }else{
                        ", their name is ${it.name}"
                    }
                    content += if (it.company.isNullOrBlank()){
                        ", we don't have their company information"
                    }else{
                        ", and they works at ${it.company}"
                    }
                    content += if (it.location.isNullOrBlank()){
                        " and finally they doesn't share their location information."
                    }else{
                        ", and finally he is currently at ${it.location} region."
                    }
                    val receive = Message("server", content)
                    val receiveItem = ReceiveItems(receive)
                    adapter.add(receiveItem)
                }else{
                    val content = "We did not find anything"
                    val receive = Message("server", content)
                    val receiveItem = ReceiveItems(receive)
                    adapter.add(receiveItem)
                }
                //kill observable so no double/triple/multiple response
                data.removeObservers(viewLifecycleOwner)
            })
        }
        /*GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            val receive = Message("server", "ご覧いただきありがとうございます！この一回きりのパフォーマンスに全身全霊で挑みました。少しでも皆さんの明日を照らす青い光となれたら嬉しいです。(ikura)")
            val receiveItem = ReceiveItems(receive)
            adapter.add(receiveItem)
        }*/
    }

    private fun initialMessage(){
        val receive = Message("server", "Type the username or !search username to search an user")
        val receiveItem = ReceiveItems(receive)
        adapter.add(receiveItem)
    }
}
