var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');
var cmtModModalElem = document.querySelector('#modal');

function enterKey(){
	if(window.event.keyCode == 13){
		regCmt();
	}
}

function regCmt() {
	var cmtVal = cmtFrmElem.cmt.value;

	var param = {
		iboard: cmtListElem.dataset.iboard,
		cmt: cmtVal
	};

	regAjax(param);
}

//서버에게 등록해줘~~~
function regAjax(param) {
	const init = {
		method: 'POST',
		body: JSON.stringify(param),
		headers:{
			'accept' : 'application/json',
			'content-type' : 'application/json;charset=UTF-8'
		}

	};

	fetch('cmt/', init)
		.then(function(res) {
			return res.json();
		})
		.then(function(myJson) {
			console.log(myJson);

			switch (myJson.result) {
				case 0:
					alert('등록 실패!');
					break;
				case 1:
					cmtFrmElem.cmt.value = '';
					getListAjax();
					alert('등록 성공!');
					break;
			}
		});
}

// 서버에게 댓글 리스트 자료 달라고 요청하는 함수
function getListAjax() {
	var iboard = cmtListElem.dataset.iboard;

	fetch('cmt/' + iboard)
		.then(function(res) {
			return res.json();
		})
		.then(function(myJson) {
			console.log(myJson);

			makeCmtElemList(myJson);
		});
}

function makeCmtElemList(data) {

	cmtListElem.innerHTML = '';
	var tableElem = document.createElement('table');
	var trElemTitle = document.createElement('tr');
	var thElemCtnt = document.createElement('th');
	var thElemWriter = document.createElement('th');
	var thElemRegdate = document.createElement('th');
	var thElemBigo = document.createElement('th');

	thElemCtnt.innerText = '내용';
	thElemWriter.innerText = '작성자';
	thElemRegdate.innerText = '작성일';
	thElemBigo.innerText = '비고';

	trElemTitle.append(thElemCtnt);
	trElemTitle.append(thElemWriter);
	trElemTitle.append(thElemRegdate);
	trElemTitle.append(thElemBigo);

	tableElem.append(trElemTitle);
	cmtListElem.append(tableElem);

	var loginUserPk = cmtListElem.dataset.loginUserPk;

	data.forEach(function(item) {
		var trElemCtnt = document.createElement('tr');
		var tdElemCtnt = document.createElement('td');
		var tdElemWriter = document.createElement('td');
		var tdElemRegdate = document.createElement('td');
		var tdElemBigo = document.createElement('td');


		tdElemCtnt.append(item.cmt);
		tdElemWriter.append(item.writerNm);
		tdElemRegdate.append(item.regdate);
		//tdElemBigo.append(item.??);

		if (parseInt(loginUserPk) === item.i_user) {
			var delBtn = document.createElement('button');
			var modBtn = document.createElement('button');

			// 삭제 버튼 클릭시 delAjax 호출
			delBtn.addEventListener('click', function() {

				if (confirm('삭제하시겠습니까?')) {
					// if문안에 내장함수 타입 -> boolean
					delAjax(item.icmt);
				}
			});

			// 수정 버튼 클릭시 
			modBtn.addEventListener('click', function() {
				// 댓글 수정 모달창 띄우기
				openModModal(item);

			});

			delBtn.innerText = '삭제';
			modBtn.innerText = '수정';

			tdElemBigo.append(delBtn);
			tdElemBigo.append(modBtn);
		}

		trElemCtnt.append(tdElemCtnt);
		trElemCtnt.append(tdElemWriter);
		trElemCtnt.append(tdElemRegdate);
		trElemCtnt.append(tdElemBigo);

		tableElem.append(trElemCtnt);
		//cmtListElem.append(tableElem);
	});

}

function delAjax(icmt) {
	fetch('cmt/' + icmt, {method: 'DELETE'})
		.then(function(res) {
			return res.json();
		})
		.then(function(data) {
			console.log(data);

			switch (data.result) {
				case 0:
					alert('댓글 삭제를 실패하였습니다.');
					break;
				case 1:
					alert('댓글 삭제 성공 !!');
					getListAjax();
					break;
			}
		});

}

function modAjax() {
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	var param = {
		icmt: cmtModFrmElem.icmt.value,
		cmt: cmtModFrmElem.modCmt.value

	}

	const init = {
		method: 'PUT',
		body: JSON.stringify(param),

		headers:{
			'accept' : 'application/json',
			'content-type' : 'application/json;charset=UTF-8'
		}
	};

	fetch('cmt', init)
		.then(function(res) {
			return res.json();
		})
		.then(function(data) {
			console.log(data);
			
			switch (data.result) {
				case 0:
					alert('수정 실패!');
					break;
				case 1:
					alert('수정 성공!');
					getListAjax();
					closeModModal();
					break;
			}
		});
}

function openModModal({ icmt, cmt }) {
	cmtModModalElem.className = '';

	console.log('icmt : ' + icmt);
	console.log('cmt : ' + cmt);

	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	cmtModFrmElem.icmt.value = icmt;
	cmtModFrmElem.modCmt.value = cmt;
}

function closeModModal() {
	cmtModModalElem.className = 'displayNone';
}

var favIconElem = document.querySelector('#favIcon');
favIconElem.addEventListener('click', function() {
	if(favIconElem.classList.contains('far')) { // X > O
		insFavAjax();
	} else { // O > X
		delFavAjax();
	}
});

//좋아요 처리
function insFavAjax() {
	const param = { iboard: cmtListElem.dataset.iboard };
	const init = {
		method: 'POST',
		body: JSON.stringify(param),
		headers:{
			'accept' : 'application/json',
			'content-type' : 'application/json;charset=UTF-8'
		}
	};
	fetch('fav', init)
		.then(function(res) {
			return res.json();
		})
		.then(function (myJson) {
			if(myJson.result === 1) {
				toggleFav(1);
			}
		})
}

//좋아요 취소
function delFavAjax() {
	const init = {
		method: 'DELETE'
	}
	const iboard = cmtListElem.dataset.iboard;

	fetch('fav?iboard=' + iboard, init)
		.then(function(res) {
			return res.json();
		})
		.then(function (myJson) {
			if(myJson.result === 1) {
				toggleFav(0);
			}
		})
}


//좋아요 여부 값 가져오기
function getFavAjax() {
	fetch('fav/' + cmtListElem.dataset.iboard)
		.then(function(res) {
			return res.json();
		})
		.then(function (myJson) {
			toggleFav(myJson.result);
		});
}

function toggleFav(toggle) {
	switch(toggle) {
		case 0: //좋아요 X
			favIconElem.classList.remove('fas');
			favIconElem.classList.add('far');
			break;
		case 1: //좋아요 O
			favIconElem.classList.remove('far');
			favIconElem.classList.add('fas');
			break;
	}
}


getListAjax(); //이 파일이 임포트되면 함수 1회 호출!
getFavAjax();