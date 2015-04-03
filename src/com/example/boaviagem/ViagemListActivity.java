package com.example.boaviagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.app.SearchManager.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ViagemListActivity extends ListActivity implements
		OnItemClickListener {

	private List<Map<String, Object>> viagens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		String[] de = { "imagem", "destino", "data", "total" };
		int[] para = { R.id.tipoViagem, R.id.destino, R.id.data, R.id.valor };

		SimpleAdapter adapter = new SimpleAdapter(this, listarViagem(),
				R.layout.lista_viagem, de, para);

		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
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
		Map<String, Object> map = viagens.get(position);
		String destino = (String) map.get("destino");
		String mensagem = "Viagem selecionada: " + destino;
		Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this, GastoListActivity.class));
	}

}
