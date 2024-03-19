import csrfFetch from "./CsrfFetch";

class CentralServerLinksProvider {
    static backendOrigin = 'http://localhost:8080/TPCourseWork_war';

    static async getLink(rel) {
        let response = await (await csrfFetch(`${CentralServerLinksProvider.backendOrigin}/api`, {'method': 'get'})).json();

        for (let i = 0; i < response.links.length; i++)
            if (response.links[i].rel === rel)
                return response.links[i].href;
        throw new Error("Link not found");
    }
}

export default CentralServerLinksProvider;