package com.hugopolog.demoappopen.ui.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hugopolog.demoappopen.R

@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()

    MainScreenContent(
        state = viewModel.screenState,
        onAction = viewModel::onAction
    )
}

@Composable
fun MainScreenContent(
    state: MainState,
    onAction: (MainActions) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        SearchAndSortBar(
            query = state.query,
            sort = state.order,
            onQueryChange = { onAction(MainActions.OnQueryChange(it)) },
            onSortChange = { onAction(MainActions.OnSortChange(it)) }
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        LottieLoader()
                    }
                }

                state.error && state.repositories.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = stringResource(R.string.error),
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { onAction(MainActions.LoadNextPage) }
                        ) {
                            Text(stringResource(R.string.retry))
                        }
                    }
                }

                state.repositories.isEmpty() -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.empty),
                            color = Color.White
                        )
                    }
                }

                else -> {
                    LazyColumn {
                        items(state.repositories.size) { index ->

                            val repo = state.repositories[index]
                            RepositoryItem(repo) {
                                onAction(MainActions.NavigateToDetail(repo))
                            }
                            if (index >= state.repositories.size - 3) {
                                onAction(MainActions.LoadNextPage)
                            }
                        }
                    }
                }
            }
        }
    }
}