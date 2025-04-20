const form = document.getElementById('postForm');
const postId = new URLSearchParams(location.search).get("id");

document.addEventListener("DOMContentLoaded", async () => {
    // ✅ 수정 모드라면 기존 글 불러와서 input 채우기
    if (postId) {
        const res = await fetch(`/api/posts/${postId}`);
        const post = await res.json();

        document.getElementById('title').value = post.title;
        document.getElementById('content').value = post.content;

        // petType (select 또는 radio)
        if (post.petType) {
            const select = document.getElementById('petType');
            if (select) select.value = post.petType;
        }

        if (document.getElementById('petName')) {
            document.getElementById('petName').value = post.petName || '';
        }

        if (document.getElementById('region')) {
            document.getElementById('region').value = post.region || '';
        }

        // 버튼 텍스트도 바꿔주면 UX 좋아요
        document.querySelector('.submit').textContent = '수정 완료';
    }
});

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const selectedPetType = document.querySelector('input[name="petType"]:checked');
    const petType = selectedPetType ? selectedPetType.value : document.getElementById('petType')?.value;

    const data = {
        title: document.getElementById('title').value,
        content: document.getElementById('content').value,
        petName: document.getElementById('petName')?.value || null,
        petType: petType || null,
        region: document.getElementById('region')?.value || null,
        postType: postId ? null : "REVIEW"  // 수정이면 굳이 다시 보낼 필요 없을 수도 있어요
    };

    const method = postId ? 'PUT' : 'POST';
    const url = postId ? `/api/posts/${postId}` : '/api/posts';

    const res = await fetch(url, {
        method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(data)
    });

    if (res.ok) {
        const json = postId ? { id: postId } : await res.json();
        alert(`글 ${postId ? "수정" : "작성"} 완료!`);
        location.href = `/post-detail.html?id=${json.id}`;
    } else {
        alert("실패 😢 로그인 상태를 확인해주세요");
    }
});
