package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_portal.*

const val REQ_PORTAL_KEY = "reqPortalKey";
const val ARG_PORTAL_TITLE = "argPortalTitle";
const val ARG_PORTAL_URL = "argPortalUrl";

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        btnAddPortal.setOnClickListener {
            val anyFieldIsEmpty = listOf(etTitle, etUrl).any { editText ->
                editText.text.toString().isBlank()
            }

            if (anyFieldIsEmpty) {
                Snackbar.make(it, getString(R.string.no_empty_fields), Snackbar.LENGTH_SHORT).show()
            } else {
                setFragmentResult(REQ_PORTAL_KEY, bundleOf(
                    Pair(ARG_PORTAL_TITLE, etTitle.text.toString()),
                    Pair(ARG_PORTAL_URL, etUrl.text.toString())
                ))
                findNavController().popBackStack()
            }
        }
    }
}