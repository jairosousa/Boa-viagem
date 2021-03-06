package com.example.boaviagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;



public class ViagemListActivity extends ListActivity implements
		OnItemClickListener, OnClickListener {

	private List<Map<String, Object>> viagens;
	private AlertDialog alertDialog;
	private AlertDialog dialogConfirmacao;
	private int viagemSelecionada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		String[] de = { "imagem", "destino", "data", "total"};
		int[] para = { R.id.tipoViagem, R.id.destino, R.id.data, R.id.valor };

		SimpleAdapter adapter = new SimpleAdapter(this, listarViagem(),
				R.layout.lista_viagem, de, para);

		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);

		this.alertDialog = criaAlertDialog();
		this.dialogConfirmacao = criaDialogConfirmacao();
	}

	private List<Map<String, Object>> listarViagem() {
		viagens = new ArrayList<Map<String, Object>>();

		Map<String, Object> item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.negocios);
		item.put("destino", "S�o Paulo");
		item.put("data", "02/02/2012 a 04/02/2012");
		item.put("total", "Gasto total R$ 314,98");
		viagens.add(item);
		item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.lazer);
		item.put("destino", "Macei�");
		item.put("data", "14/05/2012 a 22/05/2012");
		item.put("total", "Gasto total R$ 25834,67");
		viagens.add(item);
		item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.lazer);
		item.put("destino", "Bel�m");
		item.put("data", "01/01/2012 a 22/01/2012");
		item.put("total", "Gasto total R$ 12000,00");
		viagens.add(item);

		return viagens;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Map<String, Object> map = viagens.get(position);
		// String destino = (String) map.get("destino");
		// String mensagem = "Viagem selecionada: " + destino;
		// Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
		// startActivity(new Intent(this, GastoListActivity.class));
		this.viagemSelecionada = position;
		alertDialog.show();

	}

	@Override
	public void onClick(DialogInterface dialog, int item) {

		switch (item) {
		case 0:
			startActivity(new Intent(this, ViagemActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, GastoActivity.class));
			break;
		case 2:
			startActivity(new Intent(this, GastoListActivity.class));
			break;
		case 3:
			dialogConfirmacao.show();
			break;
		case DialogInterface.BUTTON_POSITIVE:
			viagens.remove(this.viagemSelecionada);
			getListView().invalidateViews();
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			dialogConfirmacao.dismiss();
			break;
		default:
			break;
		}
	}

	private AlertDialog criaAlertDialog() {

		CharSequence[] items = { getString(R.string.editar),
				getString(R.string.novo_gasto),
				getString(R.string.gastos_realizados),
				getString(R.string.remover) };

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.opcoes);
		builder.setItems(items, this);

		return builder.create();
	}

	private AlertDialog criaDialogConfirmacao() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.confirmacao_exclusao_viagem);
		builder.setPositiveButton(getString(R.string.sim), this);
		builder.setNegativeButton(getString(R.string.nao), this);

		return builder.create();
	}
	
	
}
