package com.example.moodchan

import android.content.Context
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_login.*
//import android.R
import kotlinx.android.synthetic.*
import java.io.IOError
import java.io.IOException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TITLE = "MoodChat"
private const val LOGINBUTTON = "Login"
private lateinit var firebaseAnalytics: FirebaseAnalytics

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var titleName: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var auth: FirebaseAuth

//    var mContext :Context? = null


    private var password = passwordText
//    companion object {
//        fun createInstance( mc : Context): LoginFragment {
//            // インスタンス？　MainActivityで生成時に呼ばれている関数
//            val tmpDetailFragment = LoginFragment()
//            tmpDetailFragment.mContext = mc
//            return LoginFragment()
//        }
//    }


//    private var usernameText: String? =
//    private var passwordText: String? =

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//            titleName = it.getString(TITLE)
//
//
//
////            usernameText = it.getString(ARG_PARAM1)
////            password = it.getString(ARG_PARAM2)
//        }
        //buttonLogin.setText(getString(R.string.login))
//
//        try{ resources.assets.open("normal.png").use { istream ->
//                val bitmap = BitmapFactory.decodeStream(istream)
//                imageTitle.setImageBitmap(bitmap)
//            }
//        } catch (e: IOException){
//            e.printStackTrace()
//        }
    }

    private fun toastMake(message: String, x: Int, y: Int) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        // 位置調整
        toast.setGravity(Gravity.CENTER, x, y)
        toast.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        buttonLogin.setOnClickListener {
            if (emailText.text.toString() == "" || passwordText.text.toString() == "") {
                toastMake("入力してください", 0, -200)
            } else {
                val email = emailText.text.toString()
                val password = passwordText.text.toString()
                val activity = this.activity ?: return@setOnClickListener
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful) {
                            toastMake("login成功",0,-200)
                        } else {
                            toastMake("login失敗",0,-200)
                        }
                    }
            }
        }
        buttonSignUp.setOnClickListener {
            toastMake("nyan", 0, -200)
        }


    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                //                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//            }
            }
    }
}
