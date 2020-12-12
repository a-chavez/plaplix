package cl.nodalnet.plaplix

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cl.nodalnet.plaplix.room.DetailsItem
import cl.nodalnet.plaplix.viewmodel.MyAdapter
import cl.nodalnet.plaplix.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.item_products.*

class SecondFragment : Fragment() {

    lateinit var mViewModel: MyViewModel
    var mId: Int = 1
    var mProduct = "Producto"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        arguments?.let {
            mId = it.getInt("id",1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      mViewModel.getOneGoods(mId).observe(viewLifecycleOwner, {
          mProduct=it.name
          tvSecName.setText(it.name)
          tvSecDescr.setText(it.description)
          tvSecPrice.setText(it.price.toString())
          tvSecLastPrice.setText(it.lastPrice.toString())
            when (it.credit.toString()){
                "true"-> tvSecCredit.setText("Acepta Crédito")
                else -> tvSecCredit.setText("Sólo Efectivo")
            }

          Glide.with(this)
              .load(it.image)
              .transform(CenterCrop(), RoundedCorners(20))
              .into(imgsecDetails)

          Glide.with(this)
              .load(it.image)
              .transform(CenterCrop(), RoundedCorners(10))
              .into(imgsecDetails2)

      })

        txtDescrip3.setOnClickListener {
            sendEmail()
        }

        imgEmail.setOnClickListener{
            sendEmail()
    }

    }

    fun sendEmail() {
        startActivity(
            Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:info@novaera.cl?subject= Consulta "+mProduct+" id "+mId
                        + "&body=" + Uri.encode(
                    "Hola:\n\n" + "Vi el producto: "+mProduct+" de código: "+mId+
                            " y me gustaría que me contactaran a este correo "+
                            "o al siguiente número: _________ \n\n"+ "Quedo atento."
                ))
            )
        )
    }
}