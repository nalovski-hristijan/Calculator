package com.hnalovski.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;

import com.hnalovski.calculator.databinding.ActivityMainBinding;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.displayEditText.setShowSoftInputOnFocus(false);

        binding.zeroButton.setOnClickListener(view -> updateText(getResources().getString(R.string.zeroText)));

        binding.oneButton.setOnClickListener(view -> updateText(getResources().getString(R.string.oneText)));

        binding.twoButton.setOnClickListener(view -> updateText(getResources().getString(R.string.twoText)));

        binding.threeButton.setOnClickListener(view -> updateText(getResources().getString(R.string.threeText)));

        binding.fourButton.setOnClickListener(view -> updateText(getResources().getString(R.string.fourText)));

        binding.fiveButton.setOnClickListener(view -> updateText(getResources().getString(R.string.fiveText)));

        binding.sixButton.setOnClickListener(view -> updateText(getResources().getString(R.string.sixText)));

        binding.sevenButton.setOnClickListener(view -> updateText(getResources().getString(R.string.sevenText)));

        binding.eightButton.setOnClickListener(view -> updateText(getResources().getString(R.string.eightText)));

        binding.nineButton.setOnClickListener(view -> updateText(getResources().getString(R.string.nineText)));

        binding.openParButton.setOnClickListener(view -> updateText(getResources().getString(R.string.parenthesesOpenText)));

        binding.closeParButton.setOnClickListener(view -> updateText(getResources().getString(R.string.parenthesesCloseText)));

        binding.divideButton.setOnClickListener(view -> updateText(getResources().getString(R.string.divideText)));

        binding.multiplyButton.setOnClickListener(view -> updateText(getResources().getString(R.string.multiplyText)));

        binding.minusButton.setOnClickListener(view -> updateText(getResources().getString(R.string.minusText)));

        binding.plusButton.setOnClickListener(view -> updateText(getResources().getString(R.string.plusText)));

        binding.decimalButton.setOnClickListener(view -> updateText(getResources().getString(R.string.decimalText)));

        binding.equalsButton.setOnClickListener(view -> {
            String userExpr = binding.displayEditText.getText().toString();

            binding.previousCalculation.setText(userExpr);

            userExpr = userExpr.replaceAll(getResources().getString(R.string.divideText), "/");
            userExpr = userExpr.replaceAll(getResources().getString(R.string.multiplyText), "*");

            Expression exp = new Expression(userExpr);
            String result = String.valueOf(exp.calculate());

            binding.displayEditText.setText(result);
            binding.displayEditText.setSelection(result.length());

        });

        binding.clearButton.setOnClickListener(view -> {
                    binding.displayEditText.setText("");
                    binding.previousCalculation.setText("");
                });

                binding.backspaceButton.setOnClickListener(view -> {
                    int cursorPosition = binding.displayEditText.getSelectionStart();
                    int textLen = binding.displayEditText.length();

                    if (cursorPosition != 0 && textLen != 0) {
                        SpannableStringBuilder selection = (SpannableStringBuilder) binding.displayEditText.getText();
                        selection.replace(cursorPosition - 1, cursorPosition, "");
                        binding.displayEditText.setText(selection);
                        binding.displayEditText.setSelection(cursorPosition - 1);
                    }
                });


    }

    private void updateText(String addStr) {
        String oldStr = binding.displayEditText.getText().toString();

        int cursorPosition = binding.displayEditText.getSelectionStart();

        String leftStr = oldStr.substring(0, cursorPosition);
        String rightStr = oldStr.substring(cursorPosition);

        binding.displayEditText.setText(String.format("%s%s%s", leftStr, addStr, rightStr));
        binding.displayEditText.setSelection(cursorPosition + addStr.length());
    }


    public void sinButton(View view) {
        updateText("sin(");
    }

    public void cosButton(View view) {
        updateText("cos(");
    }

    public void tanButton(View view) {
        updateText("tan(");
    }

    public void arcSinButton(View view) {
        updateText("arcsin(");
    }

    public void arcCosButton(View view) {
        updateText("arccos(");
    }

    public void arcTanButton(View view) {
        updateText("arctan(");
    }

    public void naturalLogButton(View view) {
        updateText("ln(");
    }

    public void logButton(View view) {
        updateText("log(");
    }

    public void sqrtButton(View view) {
        updateText("sqrt(");
    }

    public void absButton(View view) {
        updateText("abs(");
    }

    public void piButton(View view) {
        updateText("pi");
    }

    public void eButton(View view) {
        updateText("e");
    }

    public void xSquaredButton(View view) {
        updateText("^(2)");
    }

    public void xPowerYButton(View view) {
        updateText("^(");
    }

    public void primeButton(View view) {
        updateText("ispr(");
    }
}